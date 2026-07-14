package com.example.batch.kibanacampaigndomain;

import java.time.LocalDateTime;

public class KibanaCampaignDomain {


    private Long kibanaCampaignDomainId;
    private Long campaignId;
    private String requestId;
    private String creativeId;
    private String domainType;
    private String domain;
    private Integer clicks=0;
    private String conversionType;
    private Double CPMBid;
    private Integer impression=1;
    private Integer videoStart;
    private Integer video_25;
    private Integer video_50;
    private Integer video_75;
    private Integer video_100;
    private String exchange;
    private String impression_update_time;
    private String clicks_update_time;
    private String conversion_update_time;
    private String video_update_time;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt;

    public KibanaCampaignDomain() {
    }

    public KibanaCampaignDomain(Long kibanaCampaignDomainId, Long campaignId, String requestId, String creativeId, String domainType, String domain, Integer clicks, String conversionType, Double CPMBid, Integer impression, Integer videoStart, Integer video_25, Integer video_50, Integer video_75, Integer video_100, String exchange, String impression_update_time, String clicks_update_time, String conversion_update_time, String video_update_time, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.kibanaCampaignDomainId = kibanaCampaignDomainId;
        this.campaignId = campaignId;
        this.requestId = requestId;
        this.creativeId = creativeId;
        this.domainType = domainType;
        this.domain = domain;
        this.clicks = clicks;
        this.conversionType = conversionType;
        this.CPMBid = CPMBid;
        this.impression = impression;
        this.videoStart = videoStart;
        this.video_25 = video_25;
        this.video_50 = video_50;
        this.video_75 = video_75;
        this.video_100 = video_100;
        this.exchange = exchange;
        this.impression_update_time = impression_update_time;
        this.clicks_update_time = clicks_update_time;
        this.conversion_update_time = conversion_update_time;
        this.video_update_time = video_update_time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getKibanaCampaignDomainId() {
        return kibanaCampaignDomainId;
    }

    public void setKibanaCampaignDomainId(Long kibanaCampaignDomainId) {
        this.kibanaCampaignDomainId = kibanaCampaignDomainId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public Double getCPMBid() {
        return CPMBid;
    }

    public void setCPMBid(Double CPMBid) {
        this.CPMBid = CPMBid;
    }

    public Integer getImpression() {
        return impression;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getVideoStart() {
        return videoStart;
    }

    public void setVideoStart(Integer videoStart) {
        this.videoStart = videoStart;
    }

    public Integer getVideo_25() {
        return video_25;
    }

    public void setVideo_25(Integer video_25) {
        this.video_25 = video_25;
    }

    public Integer getVideo_50() {
        return video_50;
    }

    public void setVideo_50(Integer video_50) {
        this.video_50 = video_50;
    }

    public Integer getVideo_75() {
        return video_75;
    }

    public void setVideo_75(Integer video_75) {
        this.video_75 = video_75;
    }

    public Integer getVideo_100() {
        return video_100;
    }

    public void setVideo_100(Integer video_100) {
        this.video_100 = video_100;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getImpression_update_time() {
        return impression_update_time;
    }

    public void setImpression_update_time(String impression_update_time) {
        this.impression_update_time = impression_update_time;
    }

    public String getClicks_update_time() {
        return clicks_update_time;
    }

    public void setClicks_update_time(String clicks_update_time) {
        this.clicks_update_time = clicks_update_time;
    }

    public String getConversion_update_time() {
        return conversion_update_time;
    }

    public void setConversion_update_time(String conversion_update_time) {
        this.conversion_update_time = conversion_update_time;
    }

    public String getVideo_update_time() {
        return video_update_time;
    }

    public void setVideo_update_time(String video_update_time) {
        this.video_update_time = video_update_time;
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
