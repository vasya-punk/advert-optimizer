package com.advert.repository;

import com.advert.model.OptimizationProps;
import com.advert.model.Campaign;
import org.apache.log4j.Logger;

import java.util.*;

public class CampaignDataSource {

    private Set<Campaign> campaigns;

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }
}
