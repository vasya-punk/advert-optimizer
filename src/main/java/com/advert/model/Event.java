package com.advert.model;

public class Event {
    private String type;
    private int campaignId;
    private int publisherId;

    public Event(String type, int campaignId, int publisherId) {
        this.type = type;
        this.campaignId = campaignId;
        this.publisherId = publisherId;
    }

    public String getType() {
        return type;
    }
//    public int getTs() {
//        return ts;
//    }
    public int getCampaignId() {
        return campaignId;
    }
    public int getPublisherId() {
        return publisherId;
    }
}
