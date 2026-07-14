package com.example.batch.kibanacampaigndomain;

import java.time.LocalDate;

public class CampaignVideos {


    private Long id;
    private Campaigns campaign;
    private String placementType;
    private String rollPosition;
    private String playerSize;
    private String skippableAds;
    private String playbackMethod;
    private String rewardStatus;
    private String orientationMatching;
    private String audienceCapture;
    private String audio;
    private LocalDate updatedAt;
    private LocalDate createdAt;

    public CampaignVideos() {
    }

    public CampaignVideos(Long id, Campaigns campaign, String placementType, String rollPosition, String playerSize, String skippableAds, String playbackMethod, String rewardStatus, String orientationMatching, String audienceCapture, String audio, LocalDate updatedAt, LocalDate createdAt) {
        this.id = id;
        this.campaign = campaign;
        this.placementType = placementType;
        this.rollPosition = rollPosition;
        this.playerSize = playerSize;
        this.skippableAds = skippableAds;
        this.playbackMethod = playbackMethod;
        this.rewardStatus = rewardStatus;
        this.orientationMatching = orientationMatching;
        this.audienceCapture = audienceCapture;
        this.audio = audio;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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

    public String getPlacementType() {
        return placementType;
    }

    public void setPlacementType(String placementType) {
        this.placementType = placementType;
    }

    public String getRollPosition() {
        return rollPosition;
    }

    public void setRollPosition(String rollPosition) {
        this.rollPosition = rollPosition;
    }

    public String getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(String playerSize) {
        this.playerSize = playerSize;
    }

    public String getSkippableAds() {
        return skippableAds;
    }

    public void setSkippableAds(String skippableAds) {
        this.skippableAds = skippableAds;
    }

    public String getPlaybackMethod() {
        return playbackMethod;
    }

    public void setPlaybackMethod(String playbackMethod) {
        this.playbackMethod = playbackMethod;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public String getOrientationMatching() {
        return orientationMatching;
    }

    public void setOrientationMatching(String orientationMatching) {
        this.orientationMatching = orientationMatching;
    }

    public String getAudienceCapture() {
        return audienceCapture;
    }

    public void setAudienceCapture(String audienceCapture) {
        this.audienceCapture = audienceCapture;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
