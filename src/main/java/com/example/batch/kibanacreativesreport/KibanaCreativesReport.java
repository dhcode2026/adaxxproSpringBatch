package com.example.batch.kibanacreativesreport;

import java.time.LocalDateTime;

public class KibanaCreativesReport {

    private Long kibanaCreativesReportId;
    private String exchangeName;
    private Long creativeId;
    private Integer totalWin;
    private Integer totalRequest;
    private Integer totalResponse;
    private Integer clicks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public KibanaCreativesReport() {
    }

    public KibanaCreativesReport(Long kibanaCreativesReportId, String exchangeName, Long creativeId, Integer totalWin, Integer totalRequest, Integer totalResponse, Integer clicks, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.kibanaCreativesReportId = kibanaCreativesReportId;
        this.exchangeName = exchangeName;
        this.creativeId = creativeId;
        this.totalWin = totalWin;
        this.totalRequest = totalRequest;
        this.totalResponse = totalResponse;
        this.clicks = clicks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getKibanaCreativesReportId() {
        return kibanaCreativesReportId;
    }

    public void setKibanaCreativesReportId(Long kibanaCreativesReportId) {
        this.kibanaCreativesReportId = kibanaCreativesReportId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
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

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
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
}
