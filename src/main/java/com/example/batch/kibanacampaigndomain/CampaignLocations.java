package com.example.batch.kibanacampaigndomain;

import java.util.List;

public class CampaignLocations {


    private Long id;
    private Campaigns campaign;
    private Integer countryId;
    private Boolean selectAll;
    private List<Integer> states;
    private List<Integer> cities;

    public CampaignLocations() {
    }

    public CampaignLocations(Long id, Campaigns campaign, Integer countryId, Boolean selectAll, List<Integer> states, List<Integer> cities) {
        this.id = id;
        this.campaign = campaign;
        this.countryId = countryId;
        this.selectAll = selectAll;
        this.states = states;
        this.cities = cities;
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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Boolean getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Boolean selectAll) {
        this.selectAll = selectAll;
    }

    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public List<Integer> getCities() {
        return cities;
    }

    public void setCities(List<Integer> cities) {
        this.cities = cities;
    }
}
