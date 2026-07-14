package com.example.batch.kibanacampaigndomain;

import java.util.List;

public class CampaignInventory {


    private Long campaignInventoryId;
    private String domainappname;
    private Long domainappid;
    private String appstorename;
    private String cpmbidrange;
    private List<String> exchanges;
    private Campaigns campaign;

    public CampaignInventory() {
    }

    public CampaignInventory(Long campaignInventoryId, String domainappname, Long domainappid, String appstorename, String cpmbidrange, List<String> exchanges, Campaigns campaign) {
        this.campaignInventoryId = campaignInventoryId;
        this.domainappname = domainappname;
        this.domainappid = domainappid;
        this.appstorename = appstorename;
        this.cpmbidrange = cpmbidrange;
        this.exchanges = exchanges;
        this.campaign = campaign;
    }

    public Long getCampaignInventoryId() {
        return campaignInventoryId;
    }

    public void setCampaignInventoryId(Long campaignInventoryId) {
        this.campaignInventoryId = campaignInventoryId;
    }

    public String getDomainappname() {
        return domainappname;
    }

    public void setDomainappname(String domainappname) {
        this.domainappname = domainappname;
    }

    public Long getDomainappid() {
        return domainappid;
    }

    public void setDomainappid(Long domainappid) {
        this.domainappid = domainappid;
    }

    public String getAppstorename() {
        return appstorename;
    }

    public void setAppstorename(String appstorename) {
        this.appstorename = appstorename;
    }

    public String getCpmbidrange() {
        return cpmbidrange;
    }

    public void setCpmbidrange(String cpmbidrange) {
        this.cpmbidrange = cpmbidrange;
    }

    public List<String> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<String> exchanges) {
        this.exchanges = exchanges;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }
}
