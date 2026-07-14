package com.example.batch.kibanacampaigndomain.domainquery2;

import com.example.batch.kibanacampaigndomain.KibanaCampaignDomain;
import com.example.batch.kibanacampaigndomain.KibanaCampaignDomainReader;
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
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaCampaignDomain2Reader implements ItemReader<KibanaCampaignDomain> {




    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    public KibanaCampaignDomain2Reader(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }



    private final String url="http://data.adaxxpro.com:9200/kafka-*/_search";


//    private Double getCampaignPrice(Long campaignId) {
//        try {
//            return jdbcTemplate.queryForObject(
//                    "SELECT price FROM campaigns WHERE campaign_id = ?",
//                    Double.class,
//                    campaignId
//            );
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }


    private String requestBody2= """
            {
                    "size": 0,
                    "query": {
                      "bool": {
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
                              "campaign_id": {
                                "terms": {
                                  "field": "adid.keyword"
                                }
                              }
                            },
                            {
                              "request_id": {
                                "terms": {
                                  "field": "oidStr.keyword"
                                }
                              }
                            }
                          ]
                        },
                        "aggs": {
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
    """;

    private Iterator<KibanaCampaignDomain> iterator;


    private KibanaCampaignDomain findByCampaignIdAndRequestId(
            Long campaignId,
            String requestId) {

        String sql = """
        SELECT *
        FROM kibana_campaign_domain
        WHERE campaign_id = ?
          AND request_id = ?
        """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(KibanaCampaignDomain.class),
                    campaignId,
                    requestId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



    @Override
    public KibanaCampaignDomain read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        System.out.println("KibanaCampaignDomainReader");
        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }



    public List<KibanaCampaignDomain> callExternalApi() {

        List<KibanaCampaignDomain> flatList = new ArrayList<>();


        try {

            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity2 = new HttpEntity<>(requestBody2,headers2);

            ResponseEntity<String> responseEntity=restTemplate
                    .exchange(
                            url,
                            HttpMethod.POST,
                            entity2,
                            String.class
                    );

            String jsonResponse = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            JsonNode buckets = root.path("aggregations")
                    .path("group_by_all")
                    .path("buckets");

            for (JsonNode bucket : buckets) {

                Long campaignIdCheck=bucket
                        .path("key")
                        .path("campaign_id")
                        .asLong();

                String requestIdCheck=bucket
                        .path("key")
                        .path("request_id")
                        .asText("-");

                Integer clicks=bucket
                        .path("clicks")
                        .path("doc_count")
                        .asInt();

                KibanaCampaignDomain data=findByCampaignIdAndRequestId(campaignIdCheck,requestIdCheck);

                if(data!=null) {
                    Integer updatedClicks = data.getClicks() + clicks;
                    data.setClicks(updatedClicks);
                    flatList.add(data);
                }



            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flatList;
    }
}
