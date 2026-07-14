package com.example.batch.kibanaformula;

import com.example.batch.kibanacampaigndomain.Campaigns;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaFormulaReader implements ItemReader<KibanaFormula> {

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    public KibanaFormulaReader(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }


    private Integer findTotalBudgetById(Long campaignId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT total_budget FROM campaigns WHERE id = ?",
                    Integer.class,
                    campaignId
            );
        } catch (EmptyResultDataAccessException e) {
             throw new RuntimeException("Campaign not found", e);
        }
    }

    private Campaigns findById(Long campaignId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM campaigns WHERE id = ?",
                    (rs, rowNum) -> {

                        Campaigns campaign = new Campaigns();

                        campaign.setId(rs.getLong("id"));
                        campaign.setCustomerId(rs.getString("customer_id"));
                        campaign.setSubId(rs.getString("sub_id"));
                        campaign.setPlatformFee((float) rs.getDouble("platform_fee"));
                        campaign.setName(rs.getString("name"));
                        campaign.setStatus(rs.getString("status"));
                        campaign.setPrice(rs.getDouble("price"));
                        campaign.setCost(rs.getDouble("cost"));

                        campaign.setTotalBudget(rs.getDouble("total_budget"));
                        campaign.setDailyCost(rs.getDouble("daily_cost"));
                        campaign.setHourlyCost(rs.getDouble("hourly_cost"));
                        campaign.setDeals(convertArray(rs.getArray("deals")));
                        campaign.setBanners(convertArray(rs.getArray("banners")));
                        campaign.setVideos(convertArray(rs.getArray("videos")));
                        campaign.setAudios(convertArray(rs.getArray("audios")));
                        campaign.setNatives(convertArray(rs.getArray("natives")));
                        campaign.setRules(convertArray(rs.getArray("rules")));
                        campaign.setLinkedAds(convertArray(rs.getArray("linked_ads")));

                        return campaign;
                    },
                    campaignId
            );

        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Campaign not found", e);
        }
    }

    private List<Integer> convertArray(java.sql.Array sqlArray) {

        if (sqlArray == null) {
            return new ArrayList<>();
        }

        try {
            Integer[] values = (Integer[]) sqlArray.getArray();
            return Arrays.asList(values);

        } catch (SQLException e) {
            throw new RuntimeException("Failed converting postgres array", e);
        }
    }


    private List<Long> findCampaign() {
        return jdbcTemplate.query(
                "SELECT id FROM campaigns WHERE status = 'runnable'",
                (rs, rowNum) -> rs.getLong("id")
        );
    }

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
               "adid_unified": {
                 "type": "keyword",
                 "script": {
                   "source": "if (doc.containsKey('bidResponseData.adid.keyword') && doc['bidResponseData.adid.keyword'].size()!=0) { emit(doc['bidResponseData.adid.keyword'].value); } else if (doc.containsKey('adid.keyword') && doc['adid.keyword'].size()!=0) { emit(doc['adid.keyword'].value); }"
                 }
               }
             },
                     "query": {
                       "range": {
                         "@timestamp": {
                           "gte": "now-20m",
                           "lte": "now-5m",
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
                               "field": "adid_unified",
                               "size": 1000
                             },
                             "aggs": {
                               "total_requests": {
                                 "value_count": {
                                   "field": "adid_unified"
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
                               "clicks": {
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
                                   "click_count": {
                                     "value_count": {
                                       "field": "adid_unified"
                                     }
                                   }
                                 }
                               },
                               "pixels": {
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
                                           "type": 0
                                         }
                                       }
                                     ]
                                   }
                                 },
                                 "aggs": {
                                   "pixel_count": {
                                     "value_count": {
                                       "field": "adid_unified"
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
                                       "field": "adid_unified"
                                     }
                                   }
                                 }
                               },
                               "ctr": {
                                 "bucket_script": {
                                   "buckets_path": {
                                     "clk": "clicks>click_count",
                                     "pix": "pixels>pixel_count"
                                   },
                                   "script": "params.pix == 0 ? 0 : (params.clk / params.pix) * 100"
                                 }
                               },
                               "cvr": {
                                 "bucket_script": {
                                   "buckets_path": {
                                     "conv": "conversion_docs>conversions",
                                     "clk": "clicks>click_count"
                                   },
                                   "script": "params.clk == 0 ? 0 : (params.conv / params.clk) * 100"
                                 }
                               },
                               "win_rate_percent": {
                                 "bucket_script": {
                                   "buckets_path": {
                                     "auction_won": "response_buffer_docs>_count",
                                     "bid_request": "total_requests"
                                   },
                                   "script": "params.bid_request == 0 ? 0 : (params.auction_won / params.bid_request) * 100"
                                 }
                               },
                               "valid_campaigns_only": {
                                 "bucket_selector": {
                                   "buckets_path": {
                                     "wins": "response_buffer_docs>_count",
                                     "clicks": "clicks>click_count",
                                     "conversions": "conversion_docs>conversions"
                                   },
                                   "script": "params.wins > 0"
                                 }
                               }
                             }
                           }
                         }
                       }
                     }
                   }
            """;

    private Iterator<KibanaFormula> iterator;

    @Override
    public KibanaFormula read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            if (iterator == null) {
                System.out.println("✓ Initializing KibanaFormulaReader - calling external API");
                List<KibanaFormula> data = callExternalApi();
                System.out.println("✓ Retrieved " + data.size() + " records from external API");
                iterator = data.iterator();
            }
            if (iterator.hasNext()) {
                KibanaFormula item = iterator.next();
                System.out.println("  ✓ Reading KibanaFormula: Campaign ID = " + item.getCampaignId());
                return item;
            }
            System.out.println("✓ KibanaFormulaReader: No more items to read");
            return null;
        } catch (Exception e) {
            System.err.println("✗ Error reading KibanaFormula: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }



    public List<KibanaFormula> callExternalApi() {

        System.out.println("✓ External Api Call Started");
        List<KibanaFormula> flatList = new ArrayList<>();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            System.out.println("  ✓ Calling Kibana API at: " + url);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode().isError()) {
                System.err.println("✗ API returned error status: " + response.getStatusCode());
                return flatList;
            }

            System.out.println("  ✓ API response received successfully");

            // ... existing code ...        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            List<Long> campaignInBucket=new ArrayList<>();
            List<Long> liveCampaign = findCampaign();
//            log.info("get response: " + root);

            JsonNode buckets = root
                    .path("aggregations")
                    .path("by_exchange")
                    .path("buckets");



            if (buckets.isEmpty()) {
//                log.info("buckets is empty");
//                log.info("liveCampaign {}", liveCampaign);

                for(Long id:liveCampaign){
                    KibanaFormula kibanaFormula = new KibanaFormula(
                            id, 0, 0, 0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0,
                            BigDecimal.ZERO, 0.0, 0.0, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, null, 0.0, 0.0,
                            0.0, 0.0, 0.0, 0.0, 0.0, null);

                    flatList.add(kibanaFormula);

                }
//                log.info("Empty KibanaFormula saved with default values");
                return flatList;
            }
            for (JsonNode bucket : buckets) {

                String exchange=bucket.path("key").asText("-");

                JsonNode campaignIdBuckets = bucket.path("by_campaign")
                        .path("buckets");

                for (JsonNode campaignBucket : campaignIdBuckets) {

                    Long campaignId = campaignBucket.path("key").asLong();
                    campaignInBucket.add(campaignId);

                    Integer totalClicks = campaignBucket
                            .path("clicks")
                            .path("click_count")
                            .path("value")
                            .asInt(-1);

                    Integer totalRequest = campaignBucket
                            .path("total_requests")
                            .path("value")
                            .asInt(-1);

                    Integer conversion = campaignBucket
                            .path("conversion_docs")
                            .path("conversions")
                            .path("value")
                            .asInt(-1);

                    Integer totalPixels = campaignBucket
                            .path("pixels")
                            .path("pixel_count")
                            .path("value")
                            .asInt(0);

                    Integer totalCost = 0;

                    Integer totalWin = campaignBucket
                            .path("wins_docs")
                            .path("doc_count")
                            .asInt(0);

                    Integer totalResponse = campaignBucket
                            .path("response_buffer_docs")
                            .path("doc_count")
                            .asInt(0);

                    Double ctr = campaignBucket
                            .path("ctr")
                            .path("value")
                            .asDouble(-1);

                    Double cvr = campaignBucket
                            .path("cvr")
                            .path("value")
                            .asDouble(-1);

                    Double winPercentage = 0.0;

                    Double impressionsWon = 0.0;

                    BigDecimal totalRevenue = BigDecimal.valueOf(0);
                    BigDecimal ecpm = BigDecimal.valueOf(0);
                    Double mediaSpend = 0.0;
                    BigDecimal epc = BigDecimal.valueOf(0);
                    BigDecimal totalRpm = BigDecimal.valueOf(0);
                    BigDecimal roas = BigDecimal.valueOf(0);
                    BigDecimal revenuePerConversion = BigDecimal.valueOf(0);

                    // Log raw Kibana parsed fields
//                    log.info("==================== Campaign Raw Data ====================");
//                    log.info("Campaign ID           : {}", campaignId);
//                    log.info("Total Clicks          : {}", totalClicks);
//                    log.info("Total Request         : {}", totalRequest);
//                    log.info("Conversion            : {}", conversion);
//                    log.info("Total Pixels          : {}", totalPixels);
//                    log.info("Total Cost (default)  : {}", totalCost);
//                    log.info("Total Win             : {}, time :{}", totalWin,LocalDateTime.now());
//                    log.info("Total Response        : {}", totalResponse);
//                    log.info("CTR                   : {}", ctr);
//                    log.info("CVR                   : {}", cvr);
//                    log.info("Win Percentage (init) : {}", winPercentage);
//                    log.info("Impressions Won       : {}", impressionsWon);
//                    log.info("Total Revenue (init)  : {}", totalRevenue);
//                    log.info("eCPM (init)           : {}", ecpm);
//                    log.info("Media Spend (init)    : {}", mediaSpend);
//                    log.info("EPC (init)            : {}", epc);
//                    log.info("Total RPM (init)      : {}", totalRpm);
//                    log.info("ROAS (init)           : {}", roas);
//                    log.info("Revenue/Conversion (init): {}", revenuePerConversion);

                    Integer totalBudget = findTotalBudgetById(campaignId);
                    Campaigns campaign = findById(campaignId);

//                    log.info("Total Budget (from DB): {}", totalBudget);

                    if (campaign != null) {

                        //log.info("Campaign found in DB  : ID={}, Price={}", campaignId, campaign.getPrice());

                        Integer ecpc = 0;
                        Integer ecpa = 0;

                        try {
                            ecpc = totalBudget / totalClicks;
                            ecpa = totalBudget / conversion;
                        } catch (Exception e) {
                          //  log.warn("Division error computing eCPC/eCPA for Campaign ID: {} — defaulting to 0. Reason: {}", campaignId, e.getMessage());
                            ecpc = 0;
                            ecpa = 0;
                        }

//                        log.info("eCPC                  : {}", ecpc);
//                        log.info("eCPA                  : {}", ecpa);

                        // Cost calculation
                        Double price = campaign.getPrice();
                        Double cost = (price / 1000) * totalWin;
//                        log.info("Campaign Price        : {}", price);
//                        log.info("Calculated Cost       : {}", cost);
                        campaign.setCost(cost);
                        // campaignRepository.save(campaign);
                       // log.info("Campaign cost updated and saved for Campaign ID: {}", campaignId);

                        // Win percentage calculation
                        Double kibanaWinPercentageCalculation = (double) totalWin / totalResponse * 100;
                     //   log.info("Win Percentage (calc) : {}", kibanaWinPercentageCalculation);
                    //    System.out.println(campaign.getGroups().getGroupId()+" group camp");

                        // Platform margin calculation
                        //Groups groups = campaign.getGroups();
                       // System.out.println(groups.getGroupId()+" group");
                        Double campaignPrice = campaign.getPrice();
                        Double platformMargin = Double.valueOf(campaign.getPlatformFee());
                      //  log.info("Campaign Price (group): {}", campaignPrice);
                       // log.info("Platform Margin       : {}", platformMargin);

                        // Bid Publisher calculation
                        Double calculate = campaignPrice - platformMargin;
                        Double bidPublisher = campaignPrice - calculate / 100;
                      //  log.info("Bid Publisher         : {}", bidPublisher);

                        // Platform Fee calculation
                        Double platformFee = totalWin * platformMargin / 1000;
                       // log.info("Platform Fee          : {}", platformFee);


                        Double platformEcpm = price * platformMargin / 100;
                        Double mediaEcpm = price - platformEcpm;

                        // Media Spend calculation
                        Double MediaSpend = (totalWin / 1000.0) * mediaEcpm;
                       // log.info("Media Spend (calc)    : {}", MediaSpend);


                        // Platform Spend
                        Double platformSpend = (totalWin / 1000.0) * platformEcpm;


                        // Total Spend calculation
                        Double totalSpend = mediaSpend + platformSpend;
                       // log.info("Total Spend           : {}", totalSpend);

                        // eCPM calculation
                        Double eCPM = totalSpend / totalWin * 1000;
                       // log.info("eCPM (calc)           : {}", eCPM);


                        KibanaFormula kibanaFormula = new KibanaFormula(
                                campaignId,
                                totalClicks,
                                totalRequest,
                                conversion,
                                totalPixels,
                                totalCost,
                                totalWin,
                                totalResponse,
                                ctr,
                                cvr,
                                kibanaWinPercentageCalculation,
                                impressionsWon,
                                totalRevenue,
                                eCPM,
                                MediaSpend,
                                epc,
                                totalRpm,
                                roas,
                                revenuePerConversion,
                                ecpc,
                                ecpa,
                                campaign,
                                bidPublisher,
                                platformFee,
                                totalSpend,
                                platformMargin,
                                platformEcpm,
                                mediaEcpm,
                                platformSpend,
                                exchange
                        );



                       flatList.add(kibanaFormula);
                      //  log.info("New KibanaFormula saved for Campaign ID: {}", campaignId);
                    }

                  //  log.info("------------------------------------------------------------");
                }



            }

//            log.info("campaignInBucker {}",campaignInBucket);
//            log.info("campainInLive {}",liveCampaign);
//            for (Long id :liveCampaign)
//            {
//                if(!campaignInBucket.contains(id))
//                {
//                    KibanaFormula kibanaFormula = new KibanaFormula(id, 0, 0, 0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, BigDecimal.ZERO, 0.0, 0.0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,null);
//                    ZonedDateTime reportTime = ZonedDateTime.now(ZoneId.of("UTC"))
//                            .minusMinutes(20);
//
//                    kibanaFormula.setCreatedAt(reportTime);
//                    kibanaFormula.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
//                    kibanaFormula.setDate(reportTime.toLocalDate());
//                    kibanaFormulaRepository.save(kibanaFormula);
//                }
//            }
          //  campaignInBucket.clear();
//            liveCampaign.clear();
           // log.info("End Calling external API from KibanaFormulaApi {} ", LocalDateTime.now());


        }catch (Exception e) {
            System.err.println("✗ Error while processing KibanaFormula API response: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("✓ Finished processing KibanaFormula API response - Total records: " + flatList.size());
        return flatList;
    }
}
