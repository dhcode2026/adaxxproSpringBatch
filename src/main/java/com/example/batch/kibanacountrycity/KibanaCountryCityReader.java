package com.example.batch.kibanacountrycity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaCountryCityReader implements ItemReader<KibanaCountryCity> {


    private final RestTemplate restTemplate;
    private final String url="http://data.adaxxpro.com:9200/kafka-*/_search";
    private final String requestBody= """
            {
              "size": 0,
              "query": {
                "range": {
                  "@timestamp": {
                    "gte": "now-80m",
                    "lt": "now-20m",
                    "time_zone": "UTC"
                  }
                }
              },
              "aggs": {
                "by_exchange": {
                  "terms": {
                    "field": "exchange.keyword",
                    "size": 100
                  },
                  "aggs": {
                    "by_crid": {
                      "terms": {
                        "field": "crid.keyword",
                        "size": 10000
                      },
                      "aggs": {
                        "by_adztype": {
                          "terms": {
                            "field": "adztype.keyword",
                            "size": 100
                          },
                          "aggs": {
                            "by_adid": {
                              "terms": {
                                "field": "adid.keyword",
                                "size": 10000
                              },
                              "aggs": {
                                "by_country": {
                                  "terms": {
                                    "field": "country.keyword",
                                    "size": 1000
                                  },
                                  "aggs": {
                                    "by_city": {
                                      "terms": {
                                        "field": "city.keyword",
                                        "size": 10000
                                      },
                                      "aggs": {
                                        "wins_docs": {
                                          "filter": {
                                            "term": { "logtype.keyword": "wins" }
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
                      }
                    }
                  }
                }
              }
            }""";

    public KibanaCountryCityReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Iterator<KibanaCountryCity> iterator;


    @Override
    public KibanaCountryCity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }



    public List<KibanaCountryCity> callExternalApi() {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity =
                new HttpEntity<>(requestBody, headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );


            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            JsonNode buckets = root.path("aggregations")
                    .path("by_exchange")
                    .path("buckets");

            List<KibanaCountryCity> flatList = new ArrayList<>();


            for (JsonNode bucket : buckets) {

                String exchangeName = bucket.path("key").asText();

                JsonNode cridBuckets = bucket.path("by_crid").path("buckets");

                for (JsonNode cridBucket : cridBuckets) {

                    Integer crid = cridBucket.path("key").asInt();

                    JsonNode adzBuckets = cridBucket
                            .path("by_adztype")
                            .path("buckets");


                    for (JsonNode adzBucket : adzBuckets) {

                        String adzType = adzBucket.path("key").asText();


                        JsonNode adidBuckets = adzBucket
                                .path("by_adid")
                                .path("buckets");


                        for (JsonNode adidBucket : adidBuckets) {

                            Integer adId = adidBucket.path("key").asInt();


                            JsonNode countryBuckets = adidBucket
                                    .path("by_country")
                                    .path("buckets");


                            for (JsonNode countryBucket : countryBuckets) {

                                String country = countryBucket.path("key").asText();


                                JsonNode cityBuckets = countryBucket
                                        .path("by_city")
                                        .path("buckets");


                                for (JsonNode cityBucket : cityBuckets) {

                                    String city = cityBucket.path("key").asText();

                                    Long count = cityBucket.path("doc_count").asLong();


                                    Long winsDocs = cityBucket
                                            .path("wins_docs")
                                            .path("doc_count")
                                            .asLong();


                                    KibanaCountryCity kibanaCountryCity = new KibanaCountryCity();

                                    kibanaCountryCity.setCountry(country);
                                    kibanaCountryCity.setCity(city);
                                    kibanaCountryCity.setAdzType(adzType);
                                    kibanaCountryCity.setCreativeId(crid);
                                    kibanaCountryCity.setCampaignId(adId);
                                    kibanaCountryCity.setExchange(exchangeName);
                                    kibanaCountryCity.setWinsDocs(winsDocs);

                                    LocalDate reportTime = ZonedDateTime.now(ZoneId.of("UTC"))
                                            .minusMinutes(25)
                                            .toLocalDate();

                                    kibanaCountryCity.setDate(reportTime);


                                    flatList.add(kibanaCountryCity);


                                }
                            }
                        }
                    }
                }


            }

            return flatList;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
