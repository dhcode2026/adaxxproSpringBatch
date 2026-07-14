package com.example.batch.kibanacampaigndomain;

import java.util.Date;
import java.util.Set;

public class Groups {

    private Long groupId;
    private String name;
    private String initialStatus;
    private String budgetType;
    private Double budget;
    private String mmpToken;
    private String impressionCap;
    private Integer impressionCaps;
    private String pacing;
    private String groupBudgetOptimization;
    private String frequencyCap;
    private String advertiserSpend;
    private Double rate;
    private Double maxAmount;
    private String footfall;
    private String cubicCampaign;
    private String reach;
    private Integer status=1;
    private String KPIMetric;
    private Integer KPIValue;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;
    private Set<Campaigns> campaigns;

    public Groups() {
    }

    public Groups(Long groupId, String name, String initialStatus, String budgetType, Double budget, String mmpToken, String impressionCap, Integer impressionCaps, String pacing, String groupBudgetOptimization, String frequencyCap, String advertiserSpend, Double rate, Double maxAmount, String footfall, String cubicCampaign, String reach, Integer status, String KPIMetric, Integer KPIValue, Date startDate, Date endDate, Date createdAt, Date updatedAt, Set<Campaigns> campaigns) {
        this.groupId = groupId;
        this.name = name;
        this.initialStatus = initialStatus;
        this.budgetType = budgetType;
        this.budget = budget;
        this.mmpToken = mmpToken;
        this.impressionCap = impressionCap;
        this.impressionCaps = impressionCaps;
        this.pacing = pacing;
        this.groupBudgetOptimization = groupBudgetOptimization;
        this.frequencyCap = frequencyCap;
        this.advertiserSpend = advertiserSpend;
        this.rate = rate;
        this.maxAmount = maxAmount;
        this.footfall = footfall;
        this.cubicCampaign = cubicCampaign;
        this.reach = reach;
        this.status = status;
        this.KPIMetric = KPIMetric;
        this.KPIValue = KPIValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.campaigns = campaigns;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getMmpToken() {
        return mmpToken;
    }

    public void setMmpToken(String mmpToken) {
        this.mmpToken = mmpToken;
    }

    public String getImpressionCap() {
        return impressionCap;
    }

    public void setImpressionCap(String impressionCap) {
        this.impressionCap = impressionCap;
    }

    public Integer getImpressionCaps() {
        return impressionCaps;
    }

    public void setImpressionCaps(Integer impressionCaps) {
        this.impressionCaps = impressionCaps;
    }

    public String getPacing() {
        return pacing;
    }

    public void setPacing(String pacing) {
        this.pacing = pacing;
    }

    public String getGroupBudgetOptimization() {
        return groupBudgetOptimization;
    }

    public void setGroupBudgetOptimization(String groupBudgetOptimization) {
        this.groupBudgetOptimization = groupBudgetOptimization;
    }

    public String getFrequencyCap() {
        return frequencyCap;
    }

    public void setFrequencyCap(String frequencyCap) {
        this.frequencyCap = frequencyCap;
    }

    public String getAdvertiserSpend() {
        return advertiserSpend;
    }

    public void setAdvertiserSpend(String advertiserSpend) {
        this.advertiserSpend = advertiserSpend;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFootfall() {
        return footfall;
    }

    public void setFootfall(String footfall) {
        this.footfall = footfall;
    }

    public String getCubicCampaign() {
        return cubicCampaign;
    }

    public void setCubicCampaign(String cubicCampaign) {
        this.cubicCampaign = cubicCampaign;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKPIMetric() {
        return KPIMetric;
    }

    public void setKPIMetric(String KPIMetric) {
        this.KPIMetric = KPIMetric;
    }

    public Integer getKPIValue() {
        return KPIValue;
    }

    public void setKPIValue(Integer KPIValue) {
        this.KPIValue = KPIValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Set<Campaigns> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaigns> campaigns) {
        this.campaigns = campaigns;
    }
}
