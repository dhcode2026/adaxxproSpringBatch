package com.example.batch.kibanacampaigndomain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaCampaignDomainReader implements ItemReader<KibanaCampaignDomain> {

    private static final Log log = LogFactory.getLog(KibanaCampaignDomainReader.class);


    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    public KibanaCampaignDomainReader(RestTemplate restTemplate,JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String url="http://data.adaxxpro.com:9200/kafka-*/_search";


    private Double getCampaignPrice(Long campaignId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT price FROM campaigns WHERE id = ?",
                    Double.class,
                    campaignId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private String requestBody1= """
            {
              "size": 0,
              "query": {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "Total.logtype.keyword": "bids"
                      }
                    }
                  ],
                  "filter": [
                    {
                      "range": {
                        "@timestamp": {
                          "gte": "now-1d/d",
                          "lte": "now-1d/d",
                          "time_zone": "UTC"
                        }
                      }
                    }
                  ]
                }
              },
              "aggs": {
                "group_by_all": {
                  "composite": {
                    "size": 10000,
                    "sources": [
                      {
                        "domain": {
                          "terms": {
                            "field": "Total.domain.keyword"
                          }
                        }
                      },
                      {
                        "exchange": {
                          "terms": {
                            "field": "Total.exchange.keyword"
                          }
                        }
                      },
                      {
                        "campaign_id": {
                          "terms": {
                            "field": "Total.adid.keyword"
                          }
                        }
                      },
                      {
                        "creative_id": {
                          "terms": {
                            "field": "Total.crid.keyword"
                          }
                        }
                      },
                      {
                        "request_id": {
                          "terms": {
                            "field": "reqId.keyword"
                          }
                        }
                      }
                    ]
                  }
                }
              }
            }
            """;


    private Iterator<KibanaCampaignDomain> iterator;

    @Override
    public KibanaCampaignDomain read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        System.out.println("KibanaCampaignDomainReader");
        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }



    public List<KibanaCampaignDomain> callExternalApi() {


        String domain = null;
        String exchange = null;
        Long campaignId = 0L;
        String creativeId = null;
        String requestId = null;
        Double CPMBid = 0.0;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody1, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        String.class
                );

        List<KibanaCampaignDomain> flatList = new ArrayList<>();

        System.out.println("API Response Status: " + response.getStatusCode());

        try {

            String jsonResponse = response.getBody();
            if (jsonResponse == null || jsonResponse.isEmpty()) {
                System.out.println("WARNING: Empty response from API");
                return flatList;
            }
            System.out.println("API Response: " + jsonResponse.substring(0, Math.min(500, jsonResponse.length())));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            JsonNode buckets = root.path("aggregations")
                    .path("group_by_all")
                    .path("buckets");



            for (JsonNode bucket : buckets) {

                domain = bucket
                        .path("key")
                        .path("domain")
                        .asText("-");

                exchange = bucket
                        .path("key")
                        .path("exchange")
                        .asText("-");

                campaignId = bucket
                        .path("key")
                        .path("campaign_id")
                        .asLong();

                creativeId = bucket
                        .path("key")
                        .path("creative_id")
                        .asText("-");


                requestId = bucket
                        .path("key")
                        .path("request_id")
                        .asText("-");


                Double cpmBid = getCampaignPrice(campaignId);

                if (cpmBid != null) {
                    CPMBid = cpmBid;
                }

                KibanaCampaignDomain kibanaCampaignDomain = new KibanaCampaignDomain();
                kibanaCampaignDomain.setDomain(domain);
                kibanaCampaignDomain.setExchange(exchange);
                kibanaCampaignDomain.setCampaignId(campaignId);
                kibanaCampaignDomain.setCreativeId(creativeId);
                kibanaCampaignDomain.setRequestId(requestId);
                kibanaCampaignDomain.setCPMBid(CPMBid);

                LocalDateTime reportTime = ZonedDateTime.now(ZoneId.of("UTC"))
                        .minusMinutes(55)
                        .toLocalDateTime();

                kibanaCampaignDomain.setCreatedAt(reportTime);

                flatList.add(kibanaCampaignDomain);
            }



        } catch (Exception e) {
            log.error("Error calling Elasticsearch API: " + e.getMessage(), e);
            System.out.println("ERROR: Failed to fetch data from " + url + " - " + e.getMessage());
        }

        System.out.println("KibanaCampaignDomainReader: callExternalApi end. Total records: " + flatList.size());

        return flatList;

    }

//        try {
//
//            HttpHeaders headers2 = new HttpHeaders();
//            headers2.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> entity2 = new HttpEntity<>(requestBody2,headers2);
//
//            ResponseEntity<String> responseEntity=restTemplate
//                    .exchange(
//                            url,
//                            HttpMethod.POST,
//                            entity2,
//                            String.class
//                    );
//
//            String jsonResponse = responseEntity.getBody();
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(jsonResponse);
//
//            JsonNode buckets = root.path("aggregations")
//                    .path("group_by_all")
//                    .path("buckets");
//
//            for (JsonNode bucket : buckets) {
//
//                Long campaignIdCheck=bucket
//                        .path("key")
//                        .path("campaign_id")
//                        .asLong();
//
//                String requestIdCheck=bucket
//                        .path("key")
//                        .path("request_id")
//                        .asText("-");
//
//                Integer clicks=bucket
//                        .path("clicks")
//                        .path("doc_count")
//                        .asInt();
//
//                KibanaCampaignDomain data=kibanaCampaignDomainRepository.findByCampaignIdAndRequestId(campaignIdCheck,requestIdCheck);
//
//                if(data!=null) {
//                    Integer updatedClicks = data.getClicks() + clicks;
//                    data.setClicks(updatedClicks);
//                    kibanaCampaignDomainRepository.save(data);
//                }
//
//
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
