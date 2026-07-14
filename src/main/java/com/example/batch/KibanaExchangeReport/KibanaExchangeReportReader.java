package com.example.batch.KibanaExchangeReport;

import com.example.batch.kibanapublisher.PublishInventory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaExchangeReportReader implements ItemReader<KibanaExchangeReport> {


    private final RestTemplate restTemplate;

    public KibanaExchangeReportReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String url="http://data.adaxxpro.com:9200/kafka-*/_search";
    private final String requestBody= """
            
            {
                    "size": 0,
            
                    "runtime_mappings": {
                      "exchange_unified": {
                        "type": "keyword",
                        "script": "if (doc.containsKey('ext.exchange.keyword') && doc['ext.exchange.keyword'].size()!=0) { emit(doc['ext.exchange.keyword'].value); } else if (doc.containsKey('exchange.keyword') && doc['exchange.keyword'].size()!=0) { emit(doc['exchange.keyword'].value); } else if (doc.containsKey('publisherId.keyword') && doc['publisherId.keyword'].size()!=0) { emit(doc['publisherId.keyword'].value); }"
                      }
                    },
            
                    "query": {
                      "range": {
                        "@timestamp": {
                          "gte": "now/d",
                          "lt": "now+1d/d",
                          "time_zone": "UTC"
                        }
                      }
                    },
            
                    "aggs": {
            
                      "hourly": {
                        "date_histogram": {
                          "field": "@timestamp",
                          "calendar_interval": "hour",
                          "time_zone": "UTC",
                          "format": "yyyy-MM-dd HH:00"
                        },
            
                        "aggs": {
            
                          "by_exchange": {
                            "terms": {
                              "field": "exchange_unified",
                              "size": 100
                            },
            
                            "aggs": {
            
                              "total_request": {
                                "filter": {
                                  "term": {
                                    "logtype.keyword": "requests"
                                  }
                                }
                              },
            
            
                              "total_response": {
                                "filter": {
                                  "exists": {
                                    "field": "responseBuffer"
                                  }
                                }
                              },
            
            
                              "wins": {
                                "filter": {
                                  "term": {
                                    "logtype.keyword": "wins"
                                  }
                                }
                              },
            
            
                              "clicks": {
                                "filter": {
                                  "bool": {
                                    "must": [
                                      {
                                        "term": {
                                          "serialClass.keyword": "com.jacamars.dsp.rtb.commands.PixelClickConvertLog"
                                        }
                                      },
                                      {
                                        "term": {
                                          "type": 1
                                        }
                                      }
                                    ]
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

    private Iterator<KibanaExchangeReport> iterator;


    @Override
    public KibanaExchangeReport read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }


    public List<KibanaExchangeReport> callExternalApi() {




        try {


            // UTC day range (same as Elasticsearch)
            LocalDate today =
                    LocalDate.now(ZoneOffset.UTC);


            LocalDateTime start =
                    today.atStartOfDay();


            LocalDateTime end =
                    today.plusDays(1)
                            .atStartOfDay();



            // delete today's snapshot
          //  repository.deleteTodayData(start,end);



            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON);


            HttpEntity<String> entity =
                    new HttpEntity<>(
                            requestBody,
                            headers);



            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class);



            ObjectMapper mapper =
                    new ObjectMapper();


            JsonNode root =
                    mapper.readTree(
                            response.getBody());



            JsonNode hours =
                    root.path("aggregations")
                            .path("hourly")
                            .path("buckets");

            List<KibanaExchangeReport> flatList = new ArrayList<>();


            List<KibanaExchangeReport> reports =
                    new ArrayList<>();



            for(JsonNode hourBucket : hours){


                String hour =
                        hourBucket
                                .path("key_as_string")
                                .asText();



                JsonNode exchanges =
                        hourBucket
                                .path("by_exchange")
                                .path("buckets");



                for(JsonNode exchange : exchanges){



                    String name =
                            exchange
                                    .path("key")
                                    .asText();



                    int request =
                            exchange.path("total_request")
                                    .path("doc_count")
                                    .asInt(0);



                    int responseCount =
                            exchange.path("total_response")
                                    .path("doc_count")
                                    .asInt(0);



                    int wins =
                            exchange.path("wins")
                                    .path("doc_count")
                                    .asInt(0);



                    int clicks =
                            exchange.path("clicks")
                                    .path("doc_count")
                                    .asInt(0);



                    double fillRate =
                            request == 0
                                    ? 0
                                    : ((double)responseCount/request)*100;



                    KibanaExchangeReport report =
                            new KibanaExchangeReport();


                    report.setName(name);

                    report.setHour(hour);

                    report.setTotalRequest(request);

                    report.setTotalResponse(responseCount);

                    report.setImpression(wins);

                    report.setClicks(clicks);

                    report.setTotalFillRate(fillRate);



                    reports.add(report);


                }
            }



            flatList.addAll(reports);


        return  flatList;

        }

        catch(Exception e){

            System.out.println(e.getMessage());
        }

        return null;
    }
}
