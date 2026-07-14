package com.example.batch.kibanapublisher;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@StepScope
public class KibanaPublisherReader implements ItemReader<PublishInventory> {

    private final RestTemplate restTemplate;
    private final String url="http://data.adaxxpro.com:9200/kafka-*/_search";
    private final String requestBody=
            "{\n" +
                    "        \"size\": 0,\n" +
                    "        \"query\": {\n" +
                    "          \"range\": {\n" +
                    "            \"@timestamp\": {\n" +
                    "              \"gte\": \"now-1d\",\n" +
                    "              \"lte\": \"now\",\n" +
                    "              \"time_zone\": \"UTC\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        \"aggs\": {\n" +
                    "          \"by_publisher\": {\n" +
                    "            \"composite\": {\n" +
                    "              \"sources\": [\n" +
                    "                {\n" +
                    "                  \"publisher_id\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.publisher.id.keyword') && doc['app.publisher.id.keyword'].size() > 0) { return doc['app.publisher.id.keyword'].value; } else if (doc.containsKey('site.publisher.id.keyword') && doc['site.publisher.id.keyword'].size() > 0) { return doc['site.publisher.id.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"inventory_type\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.publisher.id.keyword') && doc['app.publisher.id.keyword'].size() > 0) { return 'app'; } else { return 'site'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"publisher_name\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.name.keyword') && doc['app.name.keyword'].size() > 0) { return doc['app.name.keyword'].value; } else if (doc.containsKey('site.domain.keyword') && doc['site.domain.keyword'].size() > 0) { return doc['site.domain.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"domain_or_bundle\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.bundle.keyword') && doc['app.bundle.keyword'].size() > 0) { return doc['app.bundle.keyword'].value; } else if (doc.containsKey('site.domain.keyword') && doc['site.domain.keyword'].size() > 0) { return doc['site.domain.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"category\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.cat.keyword') && doc['app.cat.keyword'].size() > 0) { return doc['app.cat.keyword'].value; } else if (doc.containsKey('site.cat.keyword') && doc['site.cat.keyword'].size() > 0) { return doc['site.cat.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"country\": { \"terms\": { \"field\": \"device.geo.country.keyword\" } }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"region\": { \"terms\": { \"field\": \"device.geo.region.keyword\" } }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"city\": { \"terms\": { \"field\": \"device.geo.city.keyword\" } }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"exchange\": { \"terms\": { \"field\": \"ext.exchange.keyword\" } }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"format\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('imp.video.w') && doc['imp.video.w'].size() > 0) return 'video'; if (doc.containsKey('imp.audio.mimes') && doc['imp.audio.mimes'].size() > 0) return 'audio'; if (doc.containsKey('imp.banner.w') && doc['imp.banner.w'].size() > 0) return 'banner'; if (doc.containsKey('imp.native.request.keyword') && doc['imp.native.request.keyword'].size() > 0) return 'native'; return 'unknown';\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_type\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc['device.devicetype'].size() == 0) return 'unknown'; def dt = doc['device.devicetype'].value; if (dt == 0) return 'unknown'; else if (dt == 1) return 'mobile'; else if (dt == 2) return 'desktop'; else if (dt == 3) return 'ctv'; else if (dt == 4) return 'phone'; else if (dt == 5) return 'tablet'; else if (dt == 6) return 'connected_device'; else if (dt == 7) return 'set_top_box'; else return 'other';\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"ad_size\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('imp.video.w') && doc['imp.video.w'].size() > 0 && doc.containsKey('imp.video.h') && doc['imp.video.h'].size() > 0) { return doc['imp.video.w'].value + 'x' + doc['imp.video.h'].value; } else if (doc.containsKey('imp.banner.w') && doc['imp.banner.w'].size() > 0 && doc.containsKey('imp.banner.h') && doc['imp.banner.h'].size() > 0) { return doc['imp.banner.w'].value + 'x' + doc['imp.banner.h'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_or_user_id\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('app.publisher.id.keyword') && doc['app.publisher.id.keyword'].size() > 0) { if (doc.containsKey('device.ifa.keyword') && doc['device.ifa.keyword'].size() > 0) { return doc['device.ifa.keyword'].value; } else { return 'unknown'; } } else if (doc.containsKey('site.publisher.id.keyword') && doc['site.publisher.id.keyword'].size() > 0) { if (doc.containsKey('user.id.keyword') && doc['user.id.keyword'].size() > 0) { return doc['user.id.keyword'].value; } else { return 'unknown'; } } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_make\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('device.make.keyword') && doc['device.make.keyword'].size() > 0) { return doc['device.make.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_model\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('device.model.keyword') && doc['device.model.keyword'].size() > 0) { return doc['device.model.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_os\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('device.os.keyword') && doc['device.os.keyword'].size() > 0) { return doc['device.os.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"device_os_version\": {\n" +
                    "                    \"terms\": {\n" +
                    "                      \"script\": {\n" +
                    "                        \"lang\": \"painless\",\n" +
                    "                        \"source\": \"if (doc.containsKey('device.osv.keyword') && doc['device.osv.keyword'].size() > 0) { return doc['device.osv.keyword'].value; } else { return 'unknown'; }\"\n" +
                    "                      }\n" +
                    "                    }\n" +
                    "                  }\n" +
                    "                }\n" +
                    "              ]\n" +
                    "            },\n" +
                    "            \"aggs\": {\n" +
                    "              \"observedCPM\": { \"avg\": { \"field\": \"imp.bidfloor\" } },\n" +
                    "              \"min_bidfloor\": { \"min\": { \"field\": \"imp.bidfloor\" } },\n" +
                    "              \"max_bidfloor\": { \"max\": { \"field\": \"imp.bidfloor\" } }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }";

    private Iterator<PublishInventory> iterator;

    public KibanaPublisherReader(
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;

    }

    @Override
    public PublishInventory read() throws Exception {
        if (iterator == null) {
            iterator = callExternalApi().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }

    public List<PublishInventory> callExternalApi() throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 🔥 DEBUG (VERY IMPORTANT)
        System.out.println("Kibana URL = " + url);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String jsonResponse = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);

        JsonNode buckets = root.path("aggregations")
                .path("by_publisher")
                .path("buckets");

        List<PublishInventory> flatList = new ArrayList<>();

        if (buckets.isArray()) {
            for (JsonNode bucket : buckets) {

                JsonNode key = bucket.path("key");

                PublishInventory p = new PublishInventory();
                p.setExecutionDate(LocalDate.now());

                p.setPublisherId(key.path("publisher_id").asText("-"));
                p.setAdzType(key.path("inventory_type").asText("-"));
                p.setPublisherName(key.path("publisher_name").asText("-"));
                p.setDomain(key.path("domain_or_bundle").asText("-"));
                p.setCategory(key.path("category").asText("-"));
                p.setCountry(key.path("country").asText("-"));
                p.setRegion(key.path("region").asText("-"));
                p.setCity(key.path("city").asText("-"));
                p.setExchange(key.path("exchange").asText("-"));
                p.setFormat(key.path("format").asText("-"));
                p.setDeviceType(key.path("device_type").asText("-"));
                p.setAdSize(key.path("ad_size").asText("-"));

                p.setMaxBidFloor(bucket.path("max_bidfloor").path("value").asDouble());
                p.setMinBidFloor(bucket.path("min_bidfloor").path("value").asDouble());
                p.setObservedCPM(bucket.path("observedCPM").path("value").asDouble());

                p.setDeviceId(key.path("device_or_user_id").asText("-"));
                p.setDeviceMake(key.path("device_make").asText("-"));
                p.setDeviceModel(key.path("device_model").asText("-"));
                p.setDeviceOs(key.path("device_os").asText("-"));
                p.setDeviceOsVersion(key.path("device_os_version").asText("-"));

                flatList.add(p);
            }
        }

        System.out.println("publisher inventory size = " + flatList.size());

        return flatList;

    }
}
