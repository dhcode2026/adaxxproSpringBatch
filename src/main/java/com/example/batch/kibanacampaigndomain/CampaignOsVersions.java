package com.example.batch.kibanacampaigndomain;

public class CampaignOsVersions {


    private Long id;
    private Campaigns campaign;
    private String ostype;
    private String minVersion;
    private String maxVersion;

    public CampaignOsVersions() {
    }

    public CampaignOsVersions(Long id, Campaigns campaign, String ostype, String minVersion, String maxVersion) {
        this.id = id;
        this.campaign = campaign;
        this.ostype = ostype;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
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

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public String getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }
}
