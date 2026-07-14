package com.example.batch.KibanaExchangeReport;

import java.time.LocalDateTime;

public class KibanaExchangeReport {

    private Long kibanaExchangeReportId;
    private Integer totalRequest;
    private Integer totalResponse;
    private String name;
    private double totalFillRate;
    private Integer impression;
    private Integer revenue;
    private Double mediaEcpm;
    private Integer clicks;
    private Double cmpBid;
    private Double platformMargin;
    private Double mediaSpend;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt;
    private String hour;

    public KibanaExchangeReport() {
    }

    public KibanaExchangeReport(Long kibanaExchangeReportId, Integer totalRequest, Integer totalResponse, String name, double totalFillRate, Integer impression, Integer revenue, Double mediaEcpm, Integer clicks, Double cmpBid, Double platformMargin, Double mediaSpend, LocalDateTime createdAt, LocalDateTime updatedAt, String hour) {
        this.kibanaExchangeReportId = kibanaExchangeReportId;
        this.totalRequest = totalRequest;
        this.totalResponse = totalResponse;
        this.name = name;
        this.totalFillRate = totalFillRate;
        this.impression = impression;
        this.revenue = revenue;
        this.mediaEcpm = mediaEcpm;
        this.clicks = clicks;
        this.cmpBid = cmpBid;
        this.platformMargin = platformMargin;
        this.mediaSpend = mediaSpend;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hour = hour;
    }

    public Long getKibanaExchangeReportId() {
        return kibanaExchangeReportId;
    }

    public void setKibanaExchangeReportId(Long kibanaExchangeReportId) {
        this.kibanaExchangeReportId = kibanaExchangeReportId;
    }

    public Integer getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(Integer totalRequest) {
        this.totalRequest = totalRequest;
    }

    public Integer getTotalResponse() {
        return totalResponse;
    }

    public void setTotalResponse(Integer totalResponse) {
        this.totalResponse = totalResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalFillRate() {
        return totalFillRate;
    }

    public void setTotalFillRate(double totalFillRate) {
        this.totalFillRate = totalFillRate;
    }

    public Integer getImpression() {
        return impression;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Double getMediaEcpm() {
        return mediaEcpm;
    }

    public void setMediaEcpm(Double mediaEcpm) {
        this.mediaEcpm = mediaEcpm;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Double getCmpBid() {
        return cmpBid;
    }

    public void setCmpBid(Double cmpBid) {
        this.cmpBid = cmpBid;
    }

    public Double getPlatformMargin() {
        return platformMargin;
    }

    public void setPlatformMargin(Double platformMargin) {
        this.platformMargin = platformMargin;
    }

    public Double getMediaSpend() {
        return mediaSpend;
    }

    public void setMediaSpend(Double mediaSpend) {
        this.mediaSpend = mediaSpend;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
