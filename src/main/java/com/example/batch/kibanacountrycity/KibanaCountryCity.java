package com.example.batch.kibanacountrycity;

import java.time.LocalDate;

public class KibanaCountryCity {

    private Long id;
    private String exchange;
    private Integer creativeId;
    private String adzType;
    private Integer campaignId;
    private String country;
    private String city;
    private Long winsDocs;
    private LocalDate date;

    public KibanaCountryCity() {
    }

    public KibanaCountryCity(Long id, String exchange, Integer creativeId, String adzType, Integer campaignId, String country, String city, Long winsDocs, LocalDate date) {
        this.id = id;
        this.exchange = exchange;
        this.creativeId = creativeId;
        this.adzType = adzType;
        this.campaignId = campaignId;
        this.country = country;
        this.city = city;
        this.winsDocs = winsDocs;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Integer getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Integer creativeId) {
        this.creativeId = creativeId;
    }

    public String getAdzType() {
        return adzType;
    }

    public void setAdzType(String adzType) {
        this.adzType = adzType;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getWinsDocs() {
        return winsDocs;
    }

    public void setWinsDocs(Long winsDocs) {
        this.winsDocs = winsDocs;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
