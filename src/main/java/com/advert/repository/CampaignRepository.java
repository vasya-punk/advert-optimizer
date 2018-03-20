package com.advert.repository;

import com.advert.model.Campaign;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CampaignRepository {
    Logger logger = Logger.getLogger(CampaignRepository.class);

    CampaignDataSource ds = new CampaignDataSource();

    public Set<Campaign> getCampaigns(){
        logger.debug("getCampaigns");

        return ds.getCampaigns();
    }
}
