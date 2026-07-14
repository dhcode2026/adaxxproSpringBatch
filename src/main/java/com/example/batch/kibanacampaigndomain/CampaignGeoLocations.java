package com.example.batch.kibanacampaigndomain;

public class CampaignGeoLocations {


    private Long id;
    private Campaigns campaign;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double radius;
    private Boolean target;

    public CampaignGeoLocations() {
    }

    public CampaignGeoLocations(Long id, Campaigns campaign, String address, Double latitude, Double longitude, Double radius, Boolean target) {
        this.id = id;
        this.campaign = campaign;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Boolean getTarget() {
        return target;
    }

    public void setTarget(Boolean target) {
        this.target = target;
    }
}
