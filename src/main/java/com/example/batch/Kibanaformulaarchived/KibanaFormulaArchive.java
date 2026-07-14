package com.example.batch.Kibanaformulaarchived;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class KibanaFormulaArchive {

    private Long kibanaFormulaArchiveId;
    private Long campaignId;
    private Integer totalClicks;
    private Integer totalRequest;
    private Integer conversion;
    private Integer totalPixels;
    private Integer totalCost;
    private Integer totalWin;
    private Integer totalResponse;
    private Double ctr;
    private Double cvr;
    private Double winPercentage;
    private Double impressionsWon;
    private BigDecimal totalRevenue;
    private Double ecpm;
    private Double mediaSpend;
    private BigDecimal epc;
    private BigDecimal totalRpm;
    private BigDecimal roas;
    private BigDecimal revenuePerConversion;
    private Integer totalEcpc;
    private Integer totalEcpa;
    private Double completionRate;
    private Integer startCount;
    private Integer percent25;
    private Integer percent50;
    private Integer completeCount;
    private Integer percent75;
    private Double bidPublisher;
    private Double platformFee;
    private  Double totalSpend;
    private Double platformMargin;
    private Double platformEcpm;
    private Double mediaEcpm;
    private Double platformSpend;
    private String exchange;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDate date=LocalDate.now();

    public KibanaFormulaArchive() {
    }



    public KibanaFormulaArchive(Long campaignId, int totalClicks, int totalRequest, int conversion, int totalPixels, int totalCost, int totalWin, int totalResponse, double ctr, double cvr, double winPercentage, double impressionsWon, BigDecimal totalRevenue, double ecpm, double mediaSpend, BigDecimal epc, BigDecimal totalRpm, BigDecimal roas, BigDecimal revenuePerConversion, int totalEcpc, int totalEcpa, double completionRate, int startCount, int percent25, int percent50, int completeCount, int percent75, double bidPublisher, double platformFee, double totalSpend, double platformMargin, double platformEcpm, double mediaEcpm, Double platformSpend, String exchange) {
    }

    public Long getKibanaFormulaArchiveId() {
        return kibanaFormulaArchiveId;
    }

    public void setKibanaFormulaArchiveId(Long kibanaFormulaArchiveId) {
        this.kibanaFormulaArchiveId = kibanaFormulaArchiveId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(Integer totalClicks) {
        this.totalClicks = totalClicks;
    }

    public Integer getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(Integer totalRequest) {
        this.totalRequest = totalRequest;
    }

    public Integer getConversion() {
        return conversion;
    }

    public void setConversion(Integer conversion) {
        this.conversion = conversion;
    }

    public Integer getTotalPixels() {
        return totalPixels;
    }

    public void setTotalPixels(Integer totalPixels) {
        this.totalPixels = totalPixels;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
    }

    public Integer getTotalResponse() {
        return totalResponse;
    }

    public void setTotalResponse(Integer totalResponse) {
        this.totalResponse = totalResponse;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public Double getCvr() {
        return cvr;
    }

    public void setCvr(Double cvr) {
        this.cvr = cvr;
    }

    public Double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(Double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public Double getImpressionsWon() {
        return impressionsWon;
    }

    public void setImpressionsWon(Double impressionsWon) {
        this.impressionsWon = impressionsWon;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getEcpm() {
        return ecpm;
    }

    public void setEcpm(Double ecpm) {
        this.ecpm = ecpm;
    }

    public Double getMediaSpend() {
        return mediaSpend;
    }

    public void setMediaSpend(Double mediaSpend) {
        this.mediaSpend = mediaSpend;
    }

    public BigDecimal getEpc() {
        return epc;
    }

    public void setEpc(BigDecimal epc) {
        this.epc = epc;
    }

    public BigDecimal getTotalRpm() {
        return totalRpm;
    }

    public void setTotalRpm(BigDecimal totalRpm) {
        this.totalRpm = totalRpm;
    }

    public BigDecimal getRoas() {
        return roas;
    }

    public void setRoas(BigDecimal roas) {
        this.roas = roas;
    }

    public BigDecimal getRevenuePerConversion() {
        return revenuePerConversion;
    }

    public void setRevenuePerConversion(BigDecimal revenuePerConversion) {
        this.revenuePerConversion = revenuePerConversion;
    }

    public Integer getTotalEcpc() {
        return totalEcpc;
    }

    public void setTotalEcpc(Integer totalEcpc) {
        this.totalEcpc = totalEcpc;
    }

    public Integer getTotalEcpa() {
        return totalEcpa;
    }

    public void setTotalEcpa(Integer totalEcpa) {
        this.totalEcpa = totalEcpa;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

    public Integer getStartCount() {
        return startCount;
    }

    public void setStartCount(Integer startCount) {
        this.startCount = startCount;
    }

    public Integer getPercent25() {
        return percent25;
    }

    public void setPercent25(Integer percent25) {
        this.percent25 = percent25;
    }

    public Integer getPercent50() {
        return percent50;
    }

    public void setPercent50(Integer percent50) {
        this.percent50 = percent50;
    }

    public Integer getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(Integer completeCount) {
        this.completeCount = completeCount;
    }

    public Integer getPercent75() {
        return percent75;
    }

    public void setPercent75(Integer percent75) {
        this.percent75 = percent75;
    }

    public Double getBidPublisher() {
        return bidPublisher;
    }

    public void setBidPublisher(Double bidPublisher) {
        this.bidPublisher = bidPublisher;
    }

    public Double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Double platformFee) {
        this.platformFee = platformFee;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getPlatformMargin() {
        return platformMargin;
    }

    public void setPlatformMargin(Double platformMargin) {
        this.platformMargin = platformMargin;
    }

    public Double getPlatformEcpm() {
        return platformEcpm;
    }

    public void setPlatformEcpm(Double platformEcpm) {
        this.platformEcpm = platformEcpm;
    }

    public Double getMediaEcpm() {
        return mediaEcpm;
    }

    public void setMediaEcpm(Double mediaEcpm) {
        this.mediaEcpm = mediaEcpm;
    }

    public Double getPlatformSpend() {
        return platformSpend;
    }

    public void setPlatformSpend(Double platformSpend) {
        this.platformSpend = platformSpend;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
