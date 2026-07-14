package com.example.batch.kibanacampaigndomain;

import com.example.batch.kibanaformula.KibanaFormula;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Campaigns {

    private Long id;
    private String customerId;
    private String subId;
    private Long mmpId;
    private LocalDateTime activateTime;
    private LocalDateTime expireTime;
    private Double cost;
    private List<Integer> deals;
    private String appWebId;
    private String appWebName;
    private String adDomain;
    private String mmpName;
    private Integer clicks;
    private String pagePosition;
    private List<Integer> banners;
    private List<Integer> videos;
    private String externdName;
    private List<Integer> audios;
    private List<Integer> natives;
    private List<Integer> rules;
    private String dayPartingUtc;
    private String capspec;
    private Integer capcount;
    private Integer capexpire;
    private String capunit;
    private String location;
    private Boolean excludeAdsTxt;
    private String forensiq;
    private Long agencyId;
    private Long advertiserId;
    private Long userId;
    private Double dailyCost;
    private LocalDate updatedAt;
    private Groups groups;
    private LocalDate createdAt;
    private LocalDateTime deletedAt;
    private Double hourlyCost;
    private String exchanges;
    private String regions;
    private Integer targetId;
    private Integer spendrate;
    private Integer pixels;
    private Integer wins;
    private Integer bids;
    private String name;
    private String status;
    private String conversionType;
    private Double budgetLimitDaily;
    private Double budgetLimitHourly;
    private Double totalBudget;
    private Integer bid;
    private String shard;
    private Boolean targetDirect;
    private Boolean optMade;
    private Boolean optSupply;
    private String audioType;
    private Double price;
    private String iabCategory;
    private String cpmBid;
    private Integer flightDate;
    private Integer nativeAds;
    private Boolean usebid;
    private Boolean crossDevice;
    private List<Integer> linkedAds;
    private String allTime;
    private String impressionCap;
    private String impressionType;
    private String bidShading;
    private String osversionOption;
    private Boolean deviceId;
    private Boolean userAgent;
    private String pattern;
    private String learningScope;
    private String flightStartdate;
    private String flightEnddate;
    private String serviceProvider;
    private String optimize;
    private String dollarGoal;
    private String dollarGoal1;
    private String goalStatus;
    private Boolean primaryConversion;
    private String trackConversions;
    private Boolean click_through_conversion;
    private Boolean view_through_conversion;
    private String lookBackWindow;
    private String lookBackWindow1;
    private String optimizeDomain;
    private Boolean optimizationSettings;
    private String minimumBid;
    private String bidStep;
    private String impressionThreshold;
    private String smartDisable;
    private String learnBudget;
    private String smartGoalType;
    private String conversionAt;
    private Boolean chromePrivacy;
    private String sandboxAttribution;
    private String countConversion;
    private String conversionUser;
    private String measureViewability;
    private String campaignType;
    private Double targetCpc;
    private Double actualPrice;
    private String provider;
    private String standard;
    private String samplingRate;
    private Boolean advancedVideo;
    private Boolean advancedAudio;
    private String pageFold;
    private Boolean brandProduction;
    private String captureClicks;
    private String captureConversion;
    private Boolean addOptimization;
    private String goalStr;
    private String evalutionGroup;
    private String evalutionPeriod;
    private String sampleSizeValue;
    private String sampleValue;
    private String controlGroupSize;
    private String controlGroupSov;
    private String notes;
    private String complete_25;
    private String complete_50;
    private String complete_75;
    private String complete_100;
    private String conversion;
    private Boolean brandProtection;
    private Integer totalClicks;
    private Integer totalCost;
    private Double ctr;
    private Double winPercentage;
    private Double bidshadingMultiplier;
    private Double bidMultiplier;
    private Double impressionsWon;
    private BigDecimal totalRevenue;
    private BigDecimal epc;
    private  Double totalSpend;
    private Double platformMargin;
    private CampaignTargetDevices  campaignTargetDevices;
    private Pacing  pacing;
    private CampaignVideos  campaignVideos;
    private Set<CampaignOsVersions> campaignOsVersions;
    private KibanaFormula kibanaFormula;
    private Set<CampaignInventory> campaignInventories;
    private Set<CampaignDomain> campaignDomains;
    private Set<CampaignGeoLocations>  campaignGeoLocations;
    private Set<CampaignLocations>  campaignLocations;
    private Set<CampaignConversionEvent> campaignConversionEvents;
    private List<String> blockedCategory;
    private String blockedAdvertiser;
    private String mmpImpressionTrackingUrl;
    private String mmpClickTrackingUrl;
    private Float platformFee;

    public Campaigns() {
    }

    public Campaigns(Long id, String customerId, String subId, Long mmpId, LocalDateTime activateTime, LocalDateTime expireTime, Double cost, List<Integer> deals, String appWebId, String appWebName, String adDomain, String mmpName, Integer clicks, String pagePosition, List<Integer> banners, List<Integer> videos, String externdName, List<Integer> audios, List<Integer> natives, List<Integer> rules, String dayPartingUtc, String capspec, Integer capcount, Integer capexpire, String capunit, String location, Boolean excludeAdsTxt, String forensiq, Long agencyId, Long advertiserId, Long userId, Double dailyCost, LocalDate updatedAt, LocalDate createdAt, LocalDateTime deletedAt, Double hourlyCost, String exchanges, String regions, Integer targetId, Integer spendrate, Integer pixels, Integer wins, Integer bids, String name, String status, String conversionType, Double budgetLimitDaily, Double budgetLimitHourly, Double totalBudget, Integer bid, String shard, Boolean targetDirect, Boolean optMade, Boolean optSupply, String audioType, Double price, String iabCategory, String cpmBid, Integer flightDate, Integer nativeAds, Boolean usebid, Boolean crossDevice, List<Integer> linkedAds, String allTime, String impressionCap, String impressionType, String bidShading, String osversionOption, Boolean deviceId, Boolean userAgent, String pattern, String learningScope, String flightStartdate, String flightEnddate, String serviceProvider, String optimize, String dollarGoal, String dollarGoal1, String goalStatus, Boolean primaryConversion, String trackConversions, Boolean click_through_conversion, Boolean view_through_conversion, String lookBackWindow, String lookBackWindow1, String optimizeDomain, Boolean optimizationSettings, String minimumBid, String bidStep, String impressionThreshold, String smartDisable, String learnBudget, String smartGoalType, String conversionAt, Boolean chromePrivacy, String sandboxAttribution, String countConversion, String conversionUser, String measureViewability, String campaignType, Double targetCpc, Double actualPrice, String provider, String standard, String samplingRate, Boolean advancedVideo, Boolean advancedAudio, String pageFold, Boolean brandProduction, String captureClicks, String captureConversion, Boolean addOptimization, String goalStr, String evalutionGroup, String evalutionPeriod, String sampleSizeValue, String sampleValue, String controlGroupSize, String controlGroupSov, String notes, String complete_25, String complete_50, String complete_75, String complete_100, String conversion, Boolean brandProtection, Integer totalClicks, Integer totalCost, Double ctr, Double winPercentage, Double bidshadingMultiplier, Double bidMultiplier, Double impressionsWon, BigDecimal totalRevenue, BigDecimal epc, Double totalSpend, Double platformMargin, CampaignTargetDevices campaignTargetDevices, Pacing pacing, CampaignVideos campaignVideos, Set<CampaignOsVersions> campaignOsVersions, KibanaFormula kibanaFormula, Set<CampaignInventory> campaignInventories, Set<CampaignDomain> campaignDomains, Set<CampaignGeoLocations> campaignGeoLocations, Set<CampaignLocations> campaignLocations, Set<CampaignConversionEvent> campaignConversionEvents, List<String> blockedCategory, String blockedAdvertiser, String mmpImpressionTrackingUrl, String mmpClickTrackingUrl, Float platformFee) {
        this.id = id;
        this.customerId = customerId;
        this.subId = subId;
        this.mmpId = mmpId;
        this.activateTime = activateTime;
        this.expireTime = expireTime;
        this.cost = cost;
        this.deals = deals;
        this.appWebId = appWebId;
        this.appWebName = appWebName;
        this.adDomain = adDomain;
        this.mmpName = mmpName;
        this.clicks = clicks;
        this.pagePosition = pagePosition;
        this.banners = banners;
        this.videos = videos;
        this.externdName = externdName;
        this.audios = audios;
        this.natives = natives;
        this.rules = rules;
        this.dayPartingUtc = dayPartingUtc;
        this.capspec = capspec;
        this.capcount = capcount;
        this.capexpire = capexpire;
        this.capunit = capunit;
        this.location = location;
        this.excludeAdsTxt = excludeAdsTxt;
        this.forensiq = forensiq;
        this.agencyId = agencyId;
        this.advertiserId = advertiserId;
        this.userId = userId;
        this.dailyCost = dailyCost;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.hourlyCost = hourlyCost;
        this.exchanges = exchanges;
        this.regions = regions;
        this.targetId = targetId;
        this.spendrate = spendrate;
        this.pixels = pixels;
        this.wins = wins;
        this.bids = bids;
        this.name = name;
        this.status = status;
        this.conversionType = conversionType;
        this.budgetLimitDaily = budgetLimitDaily;
        this.budgetLimitHourly = budgetLimitHourly;
        this.totalBudget = totalBudget;
        this.bid = bid;
        this.shard = shard;
        this.targetDirect = targetDirect;
        this.optMade = optMade;
        this.optSupply = optSupply;
        this.audioType = audioType;
        this.price = price;
        this.iabCategory = iabCategory;
        this.cpmBid = cpmBid;
        this.flightDate = flightDate;
        this.nativeAds = nativeAds;
        this.usebid = usebid;
        this.crossDevice = crossDevice;
        this.linkedAds = linkedAds;
        this.allTime = allTime;
        this.impressionCap = impressionCap;
        this.impressionType = impressionType;
        this.bidShading = bidShading;
        this.osversionOption = osversionOption;
        this.deviceId = deviceId;
        this.userAgent = userAgent;
        this.pattern = pattern;
        this.learningScope = learningScope;
        this.flightStartdate = flightStartdate;
        this.flightEnddate = flightEnddate;
        this.serviceProvider = serviceProvider;
        this.optimize = optimize;
        this.dollarGoal = dollarGoal;
        this.dollarGoal1 = dollarGoal1;
        this.goalStatus = goalStatus;
        this.primaryConversion = primaryConversion;
        this.trackConversions = trackConversions;
        this.click_through_conversion = click_through_conversion;
        this.view_through_conversion = view_through_conversion;
        this.lookBackWindow = lookBackWindow;
        this.lookBackWindow1 = lookBackWindow1;
        this.optimizeDomain = optimizeDomain;
        this.optimizationSettings = optimizationSettings;
        this.minimumBid = minimumBid;
        this.bidStep = bidStep;
        this.impressionThreshold = impressionThreshold;
        this.smartDisable = smartDisable;
        this.learnBudget = learnBudget;
        this.smartGoalType = smartGoalType;
        this.conversionAt = conversionAt;
        this.chromePrivacy = chromePrivacy;
        this.sandboxAttribution = sandboxAttribution;
        this.countConversion = countConversion;
        this.conversionUser = conversionUser;
        this.measureViewability = measureViewability;
        this.campaignType = campaignType;
        this.targetCpc = targetCpc;
        this.actualPrice = actualPrice;
        this.provider = provider;
        this.standard = standard;
        this.samplingRate = samplingRate;
        this.advancedVideo = advancedVideo;
        this.advancedAudio = advancedAudio;
        this.pageFold = pageFold;
        this.brandProduction = brandProduction;
        this.captureClicks = captureClicks;
        this.captureConversion = captureConversion;
        this.addOptimization = addOptimization;
        this.goalStr = goalStr;
        this.evalutionGroup = evalutionGroup;
        this.evalutionPeriod = evalutionPeriod;
        this.sampleSizeValue = sampleSizeValue;
        this.sampleValue = sampleValue;
        this.controlGroupSize = controlGroupSize;
        this.controlGroupSov = controlGroupSov;
        this.notes = notes;
        this.complete_25 = complete_25;
        this.complete_50 = complete_50;
        this.complete_75 = complete_75;
        this.complete_100 = complete_100;
        this.conversion = conversion;
        this.brandProtection = brandProtection;
        this.totalClicks = totalClicks;
        this.totalCost = totalCost;
        this.ctr = ctr;
        this.winPercentage = winPercentage;
        this.bidshadingMultiplier = bidshadingMultiplier;
        this.bidMultiplier = bidMultiplier;
        this.impressionsWon = impressionsWon;
        this.totalRevenue = totalRevenue;
        this.epc = epc;
        this.totalSpend = totalSpend;
        this.platformMargin = platformMargin;
        this.campaignTargetDevices = campaignTargetDevices;
        this.pacing = pacing;
        this.campaignVideos = campaignVideos;
        this.campaignOsVersions = campaignOsVersions;
        this.kibanaFormula = kibanaFormula;
        this.campaignInventories = campaignInventories;
        this.campaignDomains = campaignDomains;
        this.campaignGeoLocations = campaignGeoLocations;
        this.campaignLocations = campaignLocations;
        this.campaignConversionEvents = campaignConversionEvents;
        this.blockedCategory = blockedCategory;
        this.blockedAdvertiser = blockedAdvertiser;
        this.mmpImpressionTrackingUrl = mmpImpressionTrackingUrl;
        this.mmpClickTrackingUrl = mmpClickTrackingUrl;
        this.platformFee = platformFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public Long getMmpId() {
        return mmpId;
    }

    public void setMmpId(Long mmpId) {
        this.mmpId = mmpId;
    }

    public LocalDateTime getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(LocalDateTime activateTime) {
        this.activateTime = activateTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Double getCost() {
        return cost;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Integer> getDeals() {
        return deals;
    }

    public void setDeals(List<Integer> deals) {
        this.deals = deals;
    }

    public String getAppWebId() {
        return appWebId;
    }

    public void setAppWebId(String appWebId) {
        this.appWebId = appWebId;
    }

    public String getAppWebName() {
        return appWebName;
    }

    public void setAppWebName(String appWebName) {
        this.appWebName = appWebName;
    }

    public String getAdDomain() {
        return adDomain;
    }

    public void setAdDomain(String adDomain) {
        this.adDomain = adDomain;
    }

    public String getMmpName() {
        return mmpName;
    }

    public void setMmpName(String mmpName) {
        this.mmpName = mmpName;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(String pagePosition) {
        this.pagePosition = pagePosition;
    }

    public List<Integer> getBanners() {
        return banners;
    }

    public void setBanners(List<Integer> banners) {
        this.banners = banners;
    }

    public List<Integer> getVideos() {
        return videos;
    }

    public void setVideos(List<Integer> videos) {
        this.videos = videos;
    }

    public String getExterndName() {
        return externdName;
    }

    public void setExterndName(String externdName) {
        this.externdName = externdName;
    }

    public List<Integer> getAudios() {
        return audios;
    }

    public void setAudios(List<Integer> audios) {
        this.audios = audios;
    }

    public List<Integer> getNatives() {
        return natives;
    }

    public void setNatives(List<Integer> natives) {
        this.natives = natives;
    }

    public List<Integer> getRules() {
        return rules;
    }

    public void setRules(List<Integer> rules) {
        this.rules = rules;
    }

    public String getDayPartingUtc() {
        return dayPartingUtc;
    }

    public void setDayPartingUtc(String dayPartingUtc) {
        this.dayPartingUtc = dayPartingUtc;
    }

    public String getCapspec() {
        return capspec;
    }

    public void setCapspec(String capspec) {
        this.capspec = capspec;
    }

    public Integer getCapcount() {
        return capcount;
    }

    public void setCapcount(Integer capcount) {
        this.capcount = capcount;
    }

    public Integer getCapexpire() {
        return capexpire;
    }

    public void setCapexpire(Integer capexpire) {
        this.capexpire = capexpire;
    }

    public String getCapunit() {
        return capunit;
    }

    public void setCapunit(String capunit) {
        this.capunit = capunit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getExcludeAdsTxt() {
        return excludeAdsTxt;
    }

    public void setExcludeAdsTxt(Boolean excludeAdsTxt) {
        this.excludeAdsTxt = excludeAdsTxt;
    }

    public String getForensiq() {
        return forensiq;
    }

    public void setForensiq(String forensiq) {
        this.forensiq = forensiq;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(Double dailyCost) {
        this.dailyCost = dailyCost;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Double getHourlyCost() {
        return hourlyCost;
    }

    public void setHourlyCost(Double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    public String getExchanges() {
        return exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getSpendrate() {
        return spendrate;
    }

    public void setSpendrate(Integer spendrate) {
        this.spendrate = spendrate;
    }

    public Integer getPixels() {
        return pixels;
    }

    public void setPixels(Integer pixels) {
        this.pixels = pixels;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getBids() {
        return bids;
    }

    public void setBids(Integer bids) {
        this.bids = bids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public Double getBudgetLimitDaily() {
        return budgetLimitDaily;
    }

    public void setBudgetLimitDaily(Double budgetLimitDaily) {
        this.budgetLimitDaily = budgetLimitDaily;
    }

    public Double getBudgetLimitHourly() {
        return budgetLimitHourly;
    }

    public void setBudgetLimitHourly(Double budgetLimitHourly) {
        this.budgetLimitHourly = budgetLimitHourly;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getShard() {
        return shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }

    public Boolean getTargetDirect() {
        return targetDirect;
    }

    public void setTargetDirect(Boolean targetDirect) {
        this.targetDirect = targetDirect;
    }

    public Boolean getOptMade() {
        return optMade;
    }

    public void setOptMade(Boolean optMade) {
        this.optMade = optMade;
    }

    public Boolean getOptSupply() {
        return optSupply;
    }

    public void setOptSupply(Boolean optSupply) {
        this.optSupply = optSupply;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIabCategory() {
        return iabCategory;
    }

    public void setIabCategory(String iabCategory) {
        this.iabCategory = iabCategory;
    }

    public String getCpmBid() {
        return cpmBid;
    }

    public void setCpmBid(String cpmBid) {
        this.cpmBid = cpmBid;
    }

    public Integer getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Integer flightDate) {
        this.flightDate = flightDate;
    }

    public Integer getNativeAds() {
        return nativeAds;
    }

    public void setNativeAds(Integer nativeAds) {
        this.nativeAds = nativeAds;
    }

    public Boolean getUsebid() {
        return usebid;
    }

    public void setUsebid(Boolean usebid) {
        this.usebid = usebid;
    }

    public Boolean getCrossDevice() {
        return crossDevice;
    }

    public void setCrossDevice(Boolean crossDevice) {
        this.crossDevice = crossDevice;
    }

    public List<Integer> getLinkedAds() {
        return linkedAds;
    }

    public void setLinkedAds(List<Integer> linkedAds) {
        this.linkedAds = linkedAds;
    }

    public String getAllTime() {
        return allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

    public String getImpressionCap() {
        return impressionCap;
    }

    public void setImpressionCap(String impressionCap) {
        this.impressionCap = impressionCap;
    }

    public String getImpressionType() {
        return impressionType;
    }

    public void setImpressionType(String impressionType) {
        this.impressionType = impressionType;
    }

    public String getBidShading() {
        return bidShading;
    }

    public void setBidShading(String bidShading) {
        this.bidShading = bidShading;
    }

    public String getOsversionOption() {
        return osversionOption;
    }

    public void setOsversionOption(String osversionOption) {
        this.osversionOption = osversionOption;
    }

    public Boolean getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Boolean deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(Boolean userAgent) {
        this.userAgent = userAgent;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getLearningScope() {
        return learningScope;
    }

    public void setLearningScope(String learningScope) {
        this.learningScope = learningScope;
    }

    public String getFlightStartdate() {
        return flightStartdate;
    }

    public void setFlightStartdate(String flightStartdate) {
        this.flightStartdate = flightStartdate;
    }

    public String getFlightEnddate() {
        return flightEnddate;
    }

    public void setFlightEnddate(String flightEnddate) {
        this.flightEnddate = flightEnddate;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getOptimize() {
        return optimize;
    }

    public void setOptimize(String optimize) {
        this.optimize = optimize;
    }

    public String getDollarGoal() {
        return dollarGoal;
    }

    public void setDollarGoal(String dollarGoal) {
        this.dollarGoal = dollarGoal;
    }

    public String getDollarGoal1() {
        return dollarGoal1;
    }

    public void setDollarGoal1(String dollarGoal1) {
        this.dollarGoal1 = dollarGoal1;
    }

    public String getGoalStatus() {
        return goalStatus;
    }

    public void setGoalStatus(String goalStatus) {
        this.goalStatus = goalStatus;
    }

    public Boolean getPrimaryConversion() {
        return primaryConversion;
    }

    public void setPrimaryConversion(Boolean primaryConversion) {
        this.primaryConversion = primaryConversion;
    }

    public String getTrackConversions() {
        return trackConversions;
    }

    public void setTrackConversions(String trackConversions) {
        this.trackConversions = trackConversions;
    }

    public Boolean getClick_through_conversion() {
        return click_through_conversion;
    }

    public void setClick_through_conversion(Boolean click_through_conversion) {
        this.click_through_conversion = click_through_conversion;
    }

    public Boolean getView_through_conversion() {
        return view_through_conversion;
    }

    public void setView_through_conversion(Boolean view_through_conversion) {
        this.view_through_conversion = view_through_conversion;
    }

    public String getLookBackWindow() {
        return lookBackWindow;
    }

    public void setLookBackWindow(String lookBackWindow) {
        this.lookBackWindow = lookBackWindow;
    }

    public String getLookBackWindow1() {
        return lookBackWindow1;
    }

    public void setLookBackWindow1(String lookBackWindow1) {
        this.lookBackWindow1 = lookBackWindow1;
    }

    public String getOptimizeDomain() {
        return optimizeDomain;
    }

    public void setOptimizeDomain(String optimizeDomain) {
        this.optimizeDomain = optimizeDomain;
    }

    public Boolean getOptimizationSettings() {
        return optimizationSettings;
    }

    public void setOptimizationSettings(Boolean optimizationSettings) {
        this.optimizationSettings = optimizationSettings;
    }

    public String getMinimumBid() {
        return minimumBid;
    }

    public void setMinimumBid(String minimumBid) {
        this.minimumBid = minimumBid;
    }

    public String getBidStep() {
        return bidStep;
    }

    public void setBidStep(String bidStep) {
        this.bidStep = bidStep;
    }

    public String getImpressionThreshold() {
        return impressionThreshold;
    }

    public void setImpressionThreshold(String impressionThreshold) {
        this.impressionThreshold = impressionThreshold;
    }

    public String getSmartDisable() {
        return smartDisable;
    }

    public void setSmartDisable(String smartDisable) {
        this.smartDisable = smartDisable;
    }

    public String getLearnBudget() {
        return learnBudget;
    }

    public void setLearnBudget(String learnBudget) {
        this.learnBudget = learnBudget;
    }

    public String getSmartGoalType() {
        return smartGoalType;
    }

    public void setSmartGoalType(String smartGoalType) {
        this.smartGoalType = smartGoalType;
    }

    public String getConversionAt() {
        return conversionAt;
    }

    public void setConversionAt(String conversionAt) {
        this.conversionAt = conversionAt;
    }

    public Boolean getChromePrivacy() {
        return chromePrivacy;
    }

    public void setChromePrivacy(Boolean chromePrivacy) {
        this.chromePrivacy = chromePrivacy;
    }

    public String getSandboxAttribution() {
        return sandboxAttribution;
    }

    public void setSandboxAttribution(String sandboxAttribution) {
        this.sandboxAttribution = sandboxAttribution;
    }

    public String getCountConversion() {
        return countConversion;
    }

    public void setCountConversion(String countConversion) {
        this.countConversion = countConversion;
    }

    public String getConversionUser() {
        return conversionUser;
    }

    public void setConversionUser(String conversionUser) {
        this.conversionUser = conversionUser;
    }

    public String getMeasureViewability() {
        return measureViewability;
    }

    public void setMeasureViewability(String measureViewability) {
        this.measureViewability = measureViewability;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public Double getTargetCpc() {
        return targetCpc;
    }

    public void setTargetCpc(Double targetCpc) {
        this.targetCpc = targetCpc;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(String samplingRate) {
        this.samplingRate = samplingRate;
    }

    public Boolean getAdvancedVideo() {
        return advancedVideo;
    }

    public void setAdvancedVideo(Boolean advancedVideo) {
        this.advancedVideo = advancedVideo;
    }

    public Boolean getAdvancedAudio() {
        return advancedAudio;
    }

    public void setAdvancedAudio(Boolean advancedAudio) {
        this.advancedAudio = advancedAudio;
    }

    public String getPageFold() {
        return pageFold;
    }

    public void setPageFold(String pageFold) {
        this.pageFold = pageFold;
    }

    public Boolean getBrandProduction() {
        return brandProduction;
    }

    public void setBrandProduction(Boolean brandProduction) {
        this.brandProduction = brandProduction;
    }

    public String getCaptureClicks() {
        return captureClicks;
    }

    public void setCaptureClicks(String captureClicks) {
        this.captureClicks = captureClicks;
    }

    public String getCaptureConversion() {
        return captureConversion;
    }

    public void setCaptureConversion(String captureConversion) {
        this.captureConversion = captureConversion;
    }

    public Boolean getAddOptimization() {
        return addOptimization;
    }

    public void setAddOptimization(Boolean addOptimization) {
        this.addOptimization = addOptimization;
    }

    public String getGoalStr() {
        return goalStr;
    }

    public void setGoalStr(String goalStr) {
        this.goalStr = goalStr;
    }

    public String getEvalutionGroup() {
        return evalutionGroup;
    }

    public void setEvalutionGroup(String evalutionGroup) {
        this.evalutionGroup = evalutionGroup;
    }

    public String getEvalutionPeriod() {
        return evalutionPeriod;
    }

    public void setEvalutionPeriod(String evalutionPeriod) {
        this.evalutionPeriod = evalutionPeriod;
    }

    public String getSampleSizeValue() {
        return sampleSizeValue;
    }

    public void setSampleSizeValue(String sampleSizeValue) {
        this.sampleSizeValue = sampleSizeValue;
    }

    public String getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    public String getControlGroupSize() {
        return controlGroupSize;
    }

    public void setControlGroupSize(String controlGroupSize) {
        this.controlGroupSize = controlGroupSize;
    }

    public String getControlGroupSov() {
        return controlGroupSov;
    }

    public void setControlGroupSov(String controlGroupSov) {
        this.controlGroupSov = controlGroupSov;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getComplete_25() {
        return complete_25;
    }

    public void setComplete_25(String complete_25) {
        this.complete_25 = complete_25;
    }

    public String getComplete_50() {
        return complete_50;
    }

    public void setComplete_50(String complete_50) {
        this.complete_50 = complete_50;
    }

    public String getComplete_75() {
        return complete_75;
    }

    public void setComplete_75(String complete_75) {
        this.complete_75 = complete_75;
    }

    public String getComplete_100() {
        return complete_100;
    }

    public void setComplete_100(String complete_100) {
        this.complete_100 = complete_100;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public Boolean getBrandProtection() {
        return brandProtection;
    }

    public void setBrandProtection(Boolean brandProtection) {
        this.brandProtection = brandProtection;
    }

    public Integer getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(Integer totalClicks) {
        this.totalClicks = totalClicks;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public Double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(Double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public Double getBidshadingMultiplier() {
        return bidshadingMultiplier;
    }

    public void setBidshadingMultiplier(Double bidshadingMultiplier) {
        this.bidshadingMultiplier = bidshadingMultiplier;
    }

    public Double getBidMultiplier() {
        return bidMultiplier;
    }

    public void setBidMultiplier(Double bidMultiplier) {
        this.bidMultiplier = bidMultiplier;
    }

    public Double getImpressionsWon() {
        return impressionsWon;
    }

    public void setImpressionsWon(Double impressionsWon) {
        this.impressionsWon = impressionsWon;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getEpc() {
        return epc;
    }

    public void setEpc(BigDecimal epc) {
        this.epc = epc;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getPlatformMargin() {
        return platformMargin;
    }

    public void setPlatformMargin(Double platformMargin) {
        this.platformMargin = platformMargin;
    }

    public CampaignTargetDevices getCampaignTargetDevices() {
        return campaignTargetDevices;
    }

    public void setCampaignTargetDevices(CampaignTargetDevices campaignTargetDevices) {
        this.campaignTargetDevices = campaignTargetDevices;
    }

    public Pacing getPacing() {
        return pacing;
    }

    public void setPacing(Pacing pacing) {
        this.pacing = pacing;
    }

    public CampaignVideos getCampaignVideos() {
        return campaignVideos;
    }

    public void setCampaignVideos(CampaignVideos campaignVideos) {
        this.campaignVideos = campaignVideos;
    }

    public Set<CampaignOsVersions> getCampaignOsVersions() {
        return campaignOsVersions;
    }

    public void setCampaignOsVersions(Set<CampaignOsVersions> campaignOsVersions) {
        this.campaignOsVersions = campaignOsVersions;
    }

    public KibanaFormula getKibanaFormula() {
        return kibanaFormula;
    }

    public void setKibanaFormula(KibanaFormula kibanaFormula) {
        this.kibanaFormula = kibanaFormula;
    }

    public Set<CampaignInventory> getCampaignInventories() {
        return campaignInventories;
    }

    public void setCampaignInventories(Set<CampaignInventory> campaignInventories) {
        this.campaignInventories = campaignInventories;
    }

    public Set<CampaignDomain> getCampaignDomains() {
        return campaignDomains;
    }

    public void setCampaignDomains(Set<CampaignDomain> campaignDomains) {
        this.campaignDomains = campaignDomains;
    }

    public Set<CampaignGeoLocations> getCampaignGeoLocations() {
        return campaignGeoLocations;
    }

    public void setCampaignGeoLocations(Set<CampaignGeoLocations> campaignGeoLocations) {
        this.campaignGeoLocations = campaignGeoLocations;
    }

    public Set<CampaignLocations> getCampaignLocations() {
        return campaignLocations;
    }

    public void setCampaignLocations(Set<CampaignLocations> campaignLocations) {
        this.campaignLocations = campaignLocations;
    }

    public Set<CampaignConversionEvent> getCampaignConversionEvents() {
        return campaignConversionEvents;
    }

    public void setCampaignConversionEvents(Set<CampaignConversionEvent> campaignConversionEvents) {
        this.campaignConversionEvents = campaignConversionEvents;
    }

    public List<String> getBlockedCategory() {
        return blockedCategory;
    }

    public void setBlockedCategory(List<String> blockedCategory) {
        this.blockedCategory = blockedCategory;
    }

    public String getBlockedAdvertiser() {
        return blockedAdvertiser;
    }

    public void setBlockedAdvertiser(String blockedAdvertiser) {
        this.blockedAdvertiser = blockedAdvertiser;
    }

    public String getMmpImpressionTrackingUrl() {
        return mmpImpressionTrackingUrl;
    }

    public void setMmpImpressionTrackingUrl(String mmpImpressionTrackingUrl) {
        this.mmpImpressionTrackingUrl = mmpImpressionTrackingUrl;
    }

    public String getMmpClickTrackingUrl() {
        return mmpClickTrackingUrl;
    }

    public void setMmpClickTrackingUrl(String mmpClickTrackingUrl) {
        this.mmpClickTrackingUrl = mmpClickTrackingUrl;
    }

    public Float getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Float platformFee) {
        this.platformFee = platformFee;
    }
}
