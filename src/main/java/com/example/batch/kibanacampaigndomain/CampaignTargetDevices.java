package com.example.batch.kibanacampaigndomain;

import java.util.List;

public class CampaignTargetDevices {


    private Long id;
    private Campaigns campaign;
    private String carrier;
    private String browserOption;
    private String browsers;
    private String browserLanguageOption;
    private String browserLanguages;
    private String modelOption;
    private String deviceType;
    private Boolean desktop;
    private Boolean phone;
    private Boolean tablet;
    private Boolean connectedTv;
    private List<Integer> iabWhitelist;
    private String make;
    private String all_makes;
    private String model;

    public CampaignTargetDevices() {
    }

    public CampaignTargetDevices(Long id, Campaigns campaign, String carrier, String browserOption, String browsers, String browserLanguageOption, String browserLanguages, String modelOption, String deviceType, Boolean desktop, Boolean phone, Boolean tablet, Boolean connectedTv, List<Integer> iabWhitelist, String make, String all_makes, String model) {
        this.id = id;
        this.campaign = campaign;
        this.carrier = carrier;
        this.browserOption = browserOption;
        this.browsers = browsers;
        this.browserLanguageOption = browserLanguageOption;
        this.browserLanguages = browserLanguages;
        this.modelOption = modelOption;
        this.deviceType = deviceType;
        this.desktop = desktop;
        this.phone = phone;
        this.tablet = tablet;
        this.connectedTv = connectedTv;
        this.iabWhitelist = iabWhitelist;
        this.make = make;
        this.all_makes = all_makes;
        this.model = model;
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getBrowserOption() {
        return browserOption;
    }

    public void setBrowserOption(String browserOption) {
        this.browserOption = browserOption;
    }

    public String getBrowsers() {
        return browsers;
    }

    public void setBrowsers(String browsers) {
        this.browsers = browsers;
    }

    public String getBrowserLanguageOption() {
        return browserLanguageOption;
    }

    public void setBrowserLanguageOption(String browserLanguageOption) {
        this.browserLanguageOption = browserLanguageOption;
    }

    public String getBrowserLanguages() {
        return browserLanguages;
    }

    public void setBrowserLanguages(String browserLanguages) {
        this.browserLanguages = browserLanguages;
    }

    public String getModelOption() {
        return modelOption;
    }

    public void setModelOption(String modelOption) {
        this.modelOption = modelOption;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean getDesktop() {
        return desktop;
    }

    public void setDesktop(Boolean desktop) {
        this.desktop = desktop;
    }

    public Boolean getPhone() {
        return phone;
    }

    public void setPhone(Boolean phone) {
        this.phone = phone;
    }

    public Boolean getTablet() {
        return tablet;
    }

    public void setTablet(Boolean tablet) {
        this.tablet = tablet;
    }

    public Boolean getConnectedTv() {
        return connectedTv;
    }

    public void setConnectedTv(Boolean connectedTv) {
        this.connectedTv = connectedTv;
    }

    public List<Integer> getIabWhitelist() {
        return iabWhitelist;
    }

    public void setIabWhitelist(List<Integer> iabWhitelist) {
        this.iabWhitelist = iabWhitelist;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getAll_makes() {
        return all_makes;
    }

    public void setAll_makes(String all_makes) {
        this.all_makes = all_makes;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
