package com.example.batch.Kibanaformulaarchived;

import com.example.batch.kibanacampaigndomain.Campaigns;
import com.example.batch.kibanaformula.KibanaFormula;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@StepScope
public class KibanaFormulaArchiveReader implements ItemReader<KibanaFormulaArchive> {

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    public KibanaFormulaArchiveReader(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    private Set<Long> getCampaignId() {
        String sql = "SELECT campaign_id FROM kibana_formula";

        List<Long> result = jdbcTemplate.queryForList(sql, Long.class);

        return new HashSet<>(result);
    }

    private Campaigns findById(Long campaignId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM campaigns WHERE id = ?",
                    Campaigns.class,
                    campaignId
            );
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Campaign not found", e);
        }
    }

    private Set<String> findDistinctExchangesByCampaignId(Long campaignId) {
        String sql = "SELECT DISTINCT exchange FROM kibana_formula WHERE campaign_id = ?";

        List<String> result = jdbcTemplate.queryForList(
                sql,
                String.class,
                campaignId
        );

        return new HashSet<>(result);
    }


    private List<KibanaFormula> findAllByCampaignIdAndDate(
            Long campaignId,
            LocalDate date,
            String exchange) {

        String sql = "SELECT * FROM kibana_formula " +
                "WHERE campaign_id = ? " +
                "AND date = ? " +
                "AND exchange = ?";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(KibanaFormula.class),
                campaignId,
                date,
                exchange
        );
    }

    private KibanaFormulaArchive findByCampaignIdAndDateAndExchange(
            Long campaignId,
            LocalDate date,
            String exchange) {

        String sql = "SELECT * FROM kibana_formula_archive " +
                "WHERE campaign_id = ? " +
                "AND date = ? " +
                "AND exchange = ?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(KibanaFormulaArchive.class),
                    campaignId,
                    date,
                    exchange
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // or throw a custom exception if preferred
        }
    }

    private int updateKibanaFormulaArchive(KibanaFormulaArchive archive) {

        String sql = "UPDATE kibana_formula_archive SET " +
                "total_clicks = ?, " +
                "total_request = ?, " +
                "conversion = ?, " +
                "total_pixels = ?, " +
                "total_cost = ?, " +
                "total_win = ?, " +
                "total_response = ?, " +
                "ctr = ?, " +
                "cvr = ?, " +
                "win_percentage = ?, " +
                "impressions_won = ?, " +
                "total_revenue = ?, " +
                "ecpm = ?, " +
                "media_spend = ?, " +
                "epc = ?, " +
                "total_rpm = ?, " +
                "roas = ?, " +
                "revenue_per_conversion = ?, " +
                "total_ecpc = ?, " +
                "total_ecpa = ?, " +
                "completion_rate = ?, " +
                "start_count = ?, " +
                "percent25 = ?, " +
                "percent50 = ?, " +
                "complete_count = ?, " +
                "percent75 = ?, " +
                "bid_publisher = ?, " +
                "platform_fee = ?, " +
                "platform_ecpm = ?, " +
                "media_ecpm = ?, " +
                "platform_margin = ?, " +
                "platform_spend = ?, " +
                "total_spend = ?, " +
                "created_at = ?, " +
                "updated_at = ? " +
                "WHERE campaign_id = ? " +
                "AND date = ? " +
                "AND exchange = ?";

        return jdbcTemplate.update(
                sql,
                archive.getTotalClicks(),
                archive.getTotalRequest(),
                archive.getConversion(),
                archive.getTotalPixels(),
                archive.getTotalCost(),
                archive.getTotalWin(),
                archive.getTotalResponse(),
                archive.getCtr(),
                archive.getCvr(),
                archive.getWinPercentage(),
                archive.getImpressionsWon(),
                archive.getTotalRevenue(),
                archive.getEcpm(),
                archive.getMediaSpend(),
                archive.getEpc(),
                archive.getTotalRpm(),
                archive.getRoas(),
                archive.getRevenuePerConversion(),
                archive.getTotalEcpc(),
                archive.getTotalEcpa(),
                archive.getCompletionRate(),
                archive.getStartCount(),
                archive.getPercent25(),
                archive.getPercent50(),
                archive.getCompleteCount(),
                archive.getPercent75(),
                archive.getBidPublisher(),
                archive.getPlatformFee(),
                archive.getPlatformEcpm(),
                archive.getMediaEcpm(),
                archive.getPlatformMargin(),
                archive.getPlatformSpend(),
                archive.getTotalSpend(),
                archive.getCreatedAt(),
                archive.getUpdatedAt(),
                archive.getCampaignId(),
                archive.getDate(),
                archive.getExchange()
        );
    }

    private Iterator<KibanaFormulaArchive> iterator;




    @Override
    public KibanaFormulaArchive read() throws Exception {

        if (iterator == null) {
            iterator = transferData().iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }


    private List<KibanaFormulaArchive> transferData() {

        Set<Long> campaignIdList = getCampaignId();

        List<KibanaFormulaArchive> flatList = new ArrayList<>();


        for (Long campaignId : campaignIdList) {

            Set<String> exchanges =findDistinctExchangesByCampaignId(campaignId);

            if (exchanges == null || exchanges.isEmpty()) {
            System.out.println("No exchanges found for campaignId: " + campaignId);
                continue;
            }

         //   Campaigns campaign =findById(campaignId);

//            if (campaign == null) {
//              //  log.warn("Campaign not found for ID: {}", campaignId);
//                continue;
//            }

            for (String exchange : exchanges) {

                List<KibanaFormula> kibanaFormulasList =
                        findAllByCampaignIdAndDate(
                                campaignId, LocalDate.now(ZoneId.of("Asia/Kolkata")), exchange);

                if (kibanaFormulasList == null || kibanaFormulasList.isEmpty()) {
                   // log.warn("No Kibana data for campaignId: {} and exchange: {}", campaignId, exchange);
                    continue;
                }

                int totalClicks = 0;
                int totalRequest = 0;
                int conversion = 0;
                int totalPixels = 0;
                int totalCost = 0;
                int totalWin = 0;
                int totalResponse = 0;

                double ctr = 0.0;
                double cvr = 0.0;
                double winPercentage = 0.0;
                double impressionsWon = 0.0;

                BigDecimal totalRevenue = BigDecimal.ZERO;
                double ecpm = 0.0;
                double mediaSpend = 0.0;
                BigDecimal epc = BigDecimal.ZERO;
                BigDecimal totalRpm = BigDecimal.ZERO;
                BigDecimal roas = BigDecimal.ZERO;
                BigDecimal revenuePerConversion = BigDecimal.ZERO;

                int totalEcpc = 0;
                int totalEcpa = 0;

                double completionRate = 0.0;
                int startCount = 0;
                int percent25 = 0;
                int percent50 = 0;
                int completeCount = 0;
                int percent75 = 0;

                double bidPublisher = 0.0;
                double platformFee = 0.0;
                double totalSpend = 0.0;
                double platformMargin = 0.0;
                double platformEcpm = 0.0;
                double mediaEcpm = 0.0;
                Double platformSpend = 0.0;

                for (KibanaFormula kf : kibanaFormulasList) {

                    if (kf == null) continue;

                    totalClicks += Optional.ofNullable(kf.getTotalClicks()).orElse(0);
                    totalRequest += Optional.ofNullable(kf.getTotalRequest()).orElse(0);
                    conversion += Optional.ofNullable(kf.getConversion()).orElse(0);
                    totalPixels += Optional.ofNullable(kf.getTotalPixels()).orElse(0);
                    totalWin += Optional.ofNullable(kf.getTotalWin()).orElse(0);
                    totalResponse += Optional.ofNullable(kf.getTotalResponse()).orElse(0);

                    cvr += Optional.ofNullable(kf.getCvr()).orElse(0.0);

                    totalRevenue = totalRevenue.add(Optional.ofNullable(kf.getTotalRevenue()).orElse(BigDecimal.ZERO));
                    epc = epc.add(Optional.ofNullable(kf.getEpc()).orElse(BigDecimal.ZERO));
                    totalRpm = totalRpm.add(Optional.ofNullable(kf.getTotalRpm()).orElse(BigDecimal.ZERO));
                    roas = roas.add(Optional.ofNullable(kf.getRoas()).orElse(BigDecimal.ZERO));
                    revenuePerConversion = revenuePerConversion.add(Optional.ofNullable(kf.getRevenuePerConversion()).orElse(BigDecimal.ZERO));

                    totalEcpc += Optional.ofNullable(kf.getTotalEcpc()).orElse(0);
                    totalEcpa += Optional.ofNullable(kf.getTotalEcpa()).orElse(0);

                    completionRate += Optional.ofNullable(kf.getCompletionRate()).orElse(0.0);
                    startCount += Optional.ofNullable(kf.getStartCount()).orElse(0);
                    percent25 += Optional.ofNullable(kf.getPercent25()).orElse(0);
                    percent50 += Optional.ofNullable(kf.getPercent50()).orElse(0);
                    completeCount += Optional.ofNullable(kf.getCompleteCount()).orElse(0);
                    percent75 += Optional.ofNullable(kf.getPercent75()).orElse(0);
                    bidPublisher += Optional.ofNullable(kf.getBidPublisher()).orElse(0.0);
                    platformMargin = kf.getPlatformMargin();
                    platformEcpm = kf.getPlatformEcpm();
                    mediaEcpm = kf.getMediaEcpm();
                }

                platformFee = totalWin * platformMargin / 100;
                mediaSpend = totalWin / 1000.0 * mediaEcpm;
                platformSpend = totalWin / 1000.0 * platformEcpm;

                if (totalResponse > 0) {
                    winPercentage = (double) totalWin / totalResponse * 100;
                }

                impressionsWon = (double) (totalWin) / 100;

                if (impressionsWon > 0) {
                    ctr = (double) totalClicks / totalWin * 100;
                }

                totalSpend = mediaSpend + platformSpend;

                if (impressionsWon > 0) {
                    ecpm = totalSpend / impressionsWon * 1000;
                }

                totalCost = (int) (mediaSpend + platformFee);

                KibanaFormulaArchive data = findByCampaignIdAndDateAndExchange(
                                campaignId, LocalDate.now(ZoneId.of("Asia/Kolkata")), exchange);
                if (data == null) {
                    System.out.println("create new kibanaFormulaArchived data");
                    KibanaFormulaArchive archive = new KibanaFormulaArchive();
                    archive.setCampaignId(campaignId);
                    archive.setTotalClicks(totalClicks);
                    archive.setTotalRequest(totalRequest);
                    archive.setConversion(conversion);
                    archive.setTotalPixels(totalPixels);
                    archive.setTotalCost(totalCost);
                    archive.setTotalWin(totalWin);
                    archive.setTotalResponse(totalResponse);
                    archive.setCtr(ctr);
                    archive.setCvr(cvr);
                    archive.setWinPercentage(winPercentage);
                    archive.setImpressionsWon(impressionsWon);
                    archive.setTotalRevenue(totalRevenue);
                    archive.setEcpm(ecpm);
                    archive.setMediaSpend(mediaSpend);
                    archive.setEpc(epc);
                    archive.setTotalRpm(totalRpm);
                    archive.setRoas(roas);
                    archive.setRevenuePerConversion(revenuePerConversion);
                    archive.setTotalEcpc(totalEcpc);
                    archive.setTotalEcpa(totalEcpa);
                    archive.setCompletionRate(completionRate);
                    archive.setStartCount(startCount);
                    archive.setPercent25(percent25);
                    archive.setPercent50(percent50);
                    archive.setCompleteCount(completeCount);
                    archive.setPercent75(percent75);
                    archive.setBidPublisher(bidPublisher);
                    archive.setPlatformFee(platformFee);
                    archive.setTotalSpend(totalSpend);
                    archive.setPlatformMargin(platformMargin);
                    archive.setPlatformEcpm(platformEcpm);
                    archive.setMediaEcpm(mediaEcpm);
                    archive.setPlatformSpend(platformSpend);
                    archive.setExchange(exchange);
                    archive.setDate(LocalDate.now(ZoneId.of("Asia/Kolkata"))); // don't rely on the field-initializer default

                    flatList.add(archive);
                }

                else {
                    data.setCampaignId(campaignId);
                    data.setTotalClicks(totalClicks);
                    data.setTotalRequest(totalRequest);
                    data.setConversion(conversion);
                    data.setTotalPixels(totalPixels);
                    data.setTotalWin(totalWin);
                    data.setTotalCost(totalCost);
                    data.setTotalResponse(totalResponse);
                    data.setCtr(ctr);
                    data.setCvr(cvr);
                    data.setWinPercentage(winPercentage);
                    data.setImpressionsWon(impressionsWon);
                    data.setTotalRevenue(totalRevenue);
                    data.setEcpm(ecpm);
                    data.setMediaSpend(mediaSpend);
                    data.setEpc(epc);
                    data.setTotalRpm(totalRpm);
                    data.setRoas(roas);
                    data.setRevenuePerConversion(revenuePerConversion);
                    data.setTotalEcpc(totalEcpc);
                    data.setTotalEcpa(totalEcpa);
                    data.setCompletionRate(completionRate);
                    data.setStartCount(startCount);
                    data.setPercent25(percent25);
                    data.setPercent50(percent50);
                    data.setCompleteCount(completeCount);
                    data.setPercent75(percent75);
                    data.setBidPublisher(bidPublisher);
                    data.setPlatformMargin(platformMargin);
                    data.setPlatformFee(platformFee);
                    data.setTotalSpend(totalSpend);
                    data.setPlatformEcpm(platformEcpm);
                    data.setMediaEcpm(mediaEcpm);
                    data.setPlatformSpend(platformSpend);
                    data.setExchange(exchange);

                    updateKibanaFormulaArchive(data);
                }

             //   log.info("Saved archive for campaignId: {} and exchange: {}", campaignId, exchange);
            }
        }

        return flatList;
    }
}
