package com.example.batch.kibanacampaigndomain;

import java.util.Date;
import java.util.List;

public class CampaignDomain {

    private Long campaignDomainId;
    private Long domainListId;
    private String name;
    private String listType;
    private String domainListCount;
    private List<String> domains;
    private String fileData;
    private Date createdAt;
    private Date updatedAt;
    private Boolean checked;
    private Campaigns campaign;

    public CampaignDomain() {
    }

    public CampaignDomain(Long campaignDomainId, Long domainListId, String name, String listType, String domainListCount, List<String> domains, String fileData, Date createdAt, Date updatedAt, Boolean checked, Campaigns campaign) {
        this.campaignDomainId = campaignDomainId;
        this.domainListId = domainListId;
        this.name = name;
        this.listType = listType;
        this.domainListCount = domainListCount;
        this.domains = domains;
        this.fileData = fileData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.checked = checked;
        this.campaign = campaign;
    }

    public Long getCampaignDomainId() {
        return campaignDomainId;
    }

    public void setCampaignDomainId(Long campaignDomainId) {
        this.campaignDomainId = campaignDomainId;
    }

    public Long getDomainListId() {
        return domainListId;
    }

    public void setDomainListId(Long domainListId) {
        this.domainListId = domainListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getDomainListCount() {
        return domainListCount;
    }

    public void setDomainListCount(String domainListCount) {
        this.domainListCount = domainListCount;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }
}
