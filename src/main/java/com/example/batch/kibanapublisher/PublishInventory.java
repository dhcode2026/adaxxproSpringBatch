package com.example.batch.kibanapublisher;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class PublishInventory {


    private Long publishInventoryId;
    private String publisherId;
    private String adzType;
    private String publisherName;
    private String domain;
    private String category;
    private String country;
    private String region;
    private String city;
    private String exchange;
    private String format;
    private String deviceType;
    private String deviceMake;
    private String deviceModel;
    private String deviceOs;
    private String deviceOsVersion;
    private String adSize;
    private String deviceId;
    private Double maxBidFloor;
    private Double minBidFloor;
    private Double observedCPM;
    private LocalDate executionDate;
    private Date createdAt=new Date();
    private Date updatedAt;

    public PublishInventory() {
    }

    public PublishInventory(Long publishInventoryId, String publisherId, String adzType, String publisherName, String domain, String category, String country, String region, String city, String exchange, String format, String deviceType, String deviceMake, String deviceModel, String deviceOs, String deviceOsVersion, String adSize, String deviceId, Double maxBidFloor, Double minBidFloor, Double observedCPM, LocalDate executionDate, Date createdAt, Date updatedAt) {
        this.publishInventoryId = publishInventoryId;
        this.publisherId = publisherId;
        this.adzType = adzType;
        this.publisherName = publisherName;
        this.domain = domain;
        this.category = category;
        this.country = country;
        this.region = region;
        this.city = city;
        this.exchange = exchange;
        this.format = format;
        this.deviceType = deviceType;
        this.deviceMake = deviceMake;
        this.deviceModel = deviceModel;
        this.deviceOs = deviceOs;
        this.deviceOsVersion = deviceOsVersion;
        this.adSize = adSize;
        this.deviceId = deviceId;
        this.maxBidFloor = maxBidFloor;
        this.minBidFloor = minBidFloor;
        this.observedCPM = observedCPM;
        this.executionDate = executionDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getPublishInventoryId() {
        return publishInventoryId;
    }

    public void setPublishInventoryId(Long publishInventoryId) {
        this.publishInventoryId = publishInventoryId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getAdzType() {
        return adzType;
    }

    public void setAdzType(String adzType) {
        this.adzType = adzType;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceMake() {
        return deviceMake;
    }

    public void setDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    public String getAdSize() {
        return adSize;
    }

    public void setAdSize(String adSize) {
        this.adSize = adSize;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getMaxBidFloor() {
        return maxBidFloor;
    }

    public void setMaxBidFloor(Double maxBidFloor) {
        this.maxBidFloor = maxBidFloor;
    }

    public Double getMinBidFloor() {
        return minBidFloor;
    }

    public void setMinBidFloor(Double minBidFloor) {
        this.minBidFloor = minBidFloor;
    }

    public Double getObservedCPM() {
        return observedCPM;
    }

    public void setObservedCPM(Double observedCPM) {
        this.observedCPM = observedCPM;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
