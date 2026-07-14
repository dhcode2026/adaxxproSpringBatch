package com.example.batch.kibanacreativesreport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaCreativesReader implements ItemReader<KibanaCreativesReport> {

    private RestTemplate restTemplate;
    private String url="http://data.adaxxpro.com:9200/kafka-*/_search";
    private String requestBody= """
            {
                    "size": 0,
                    "runtime_mappings": {
              "exchange_unified": {
                "type": "keyword",
                "script": {
                  "source": "if (doc.containsKey('bidResponseData.exchange.keyword') && doc['bidResponseData.exchange.keyword'].size()!=0) { emit(doc['bidResponseData.exchange.keyword'].value); } else if (doc.containsKey('exchange.keyword') && doc['exchange.keyword'].size()!=0) { emit(doc['exchange.keyword'].value); }"
                }
              },
              "crid_unified": {
                "type": "keyword",
                "script": {
                  "source": "if (doc.containsKey('bidResponseData.crid.keyword') && doc['bidResponseData.crid.keyword'].size()!=0) { emit(doc['bidResponseData.crid.keyword'].value); } else if (doc.containsKey('crid.keyword') && doc['crid.keyword'].size()!=0) { emit(doc['crid.keyword'].value); }"
                }
              }
            },
                    "query": {
                      "range": {
                        "@timestamp": {
                          "gte": "now-90m",
                          "lte": "now-30m",
                          "time_zone": "UTC"
                        }
                      }
                    },
                    "aggs": {
                      "by_exchange": {
                        "terms": {
                          "field": "exchange_unified",
                          "size": 100
                        },
                        "aggs": {
                          "by_campaign": {
                            "terms": {
                              "field": "crid_unified"
                            },
                            "aggs": {
                              "total_requests": {
                                "value_count": {
                                "field": "crid_unified"
                                }
                              },
                              "wins_docs": {
                                "filter": {
                                  "term": {
                                    "logtype.keyword": "wins"
                                  }
                                },
                                "aggs": {
                                  "unique_wins": {
                                    "cardinality": {
                                      "field": "oidStr.keyword",
                                      "precision_threshold": 40000
                                    }
                                  }
                                }
                              },
                              "response_buffer_docs": {
                               "filter": {
                                  "term": {
                                    "logtype.keyword": "bidResponse"
                                  }
                                }
                              },
                              "click_docs": {
                                "filter": {
                                  "bool": {
                                    "must": [
                                      {
                                        "term": {
                                          "serialClass.keyword": "com.adzimpression.dsp.rtb.commands.PixelClickConvertLog"
                                        }
                                      },
                                      {
                                        "term": {
                                          "type": 1
                                        }
                                      }
                                    ]
                                  }
                                },
                                "aggs": {
                                  "click": {
                                    "value_count": {
                                        "field": "crid_unified"
                                    }
                                    }
                                }
                              },
                              "conversion_docs": {
                                "filter": {
                                  "bool": {
                                    "must": [
                                      {
                                        "term": {
                                          "serialClass.keyword": "com.adzimpression.dsp.rtb.commands.PixelClickConvertLog"
                                        }
                                      },
                                      {
                                        "term": {
                                          "type": 2
                                        }
                                      }
                                    ]
                                  }
                                },
                                "aggs": {
                                  "conversions": {
                                    "value_count": {
                                      "field": "crid.keyword"
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
               }
            """;

    private Iterator<KibanaCreativesReport> iterator;


    public KibanaCreativesReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public KibanaCreativesReport read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }



    public List<KibanaCreativesReport> callExternalApi() {


        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);


            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getBody() == null) {
                return new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            JsonNode buckets = root.path("aggregations")
                    .path("by_exchange")
                    .path("buckets");

            List<KibanaCreativesReport> flatList = new ArrayList<>();

            for (JsonNode node : buckets) {

                String exchangeName = node.path("key").asText("-");

                JsonNode creatives = node.path("by_campaign").path("buckets");

                for (JsonNode creative : creatives) {

                    Long creativeId = creative.path("key").asLong();
                    Integer clicks = creative.path("click_docs").path("click").path("value").asInt();
                    Integer totalRequests = creative.path("total_requests").path("value").asInt();
                    Integer totalWins = creative.path("wins_docs").path("doc_count").asInt();
                    Integer totalResponse = creative.path("response_buffer_docs").path("doc_count").asInt();


                    KibanaCreativesReport report = new KibanaCreativesReport();
                    report.setExchangeName(exchangeName);
                    report.setCreativeId(creativeId);
                    report.setTotalRequest(totalRequests);
                    report.setTotalWin(totalWins);
                    report.setTotalResponse(totalResponse);
                    report.setClicks(clicks);

                    LocalDateTime reportTime = ZonedDateTime.now(ZoneId.of("UTC"))
                            .minusMinutes(35)
                            .toLocalDateTime();

                    report.setCreatedAt(reportTime);

                    flatList.add(report);
                }
            }

            return flatList;


        } catch (Exception ex) {

            throw new RuntimeException(ex);
        }
    }
}
