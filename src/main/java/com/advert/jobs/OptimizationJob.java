package com.advert.jobs;

import com.advert.model.OptimizationProps;
import com.advert.model.Campaign;
import com.advert.model.Event;
import com.advert.repository.CampaignRepository;
import com.advert.repository.EventRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OptimizationJob {

    Logger logger = Logger.getLogger(OptimizationJob.class);

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    EventRepository eventRepository;

    @Scheduled(fixedRate = 3600000)
    public void executeInternal() {
        logger.info("Starting OptimizationJob");

        Set<Campaign> campaigns = campaignRepository.getCampaigns();
        /** query results from two weeks ago */
        Set<Event> events = eventRepository.getEventsSince(3600 * 24 * 14);

        logger.info("events aggregation");
        // Map<campaignId, Map<publisherId, Pair<cntSource, cntMeasure>>>
        Map<Integer, Map<Integer, List<Event>>> campaignsMap = events.stream().collect(Collectors.groupingBy(Event::getCampaignId, Collectors.groupingBy(Event::getPublisherId)));
        campaignsMap.entrySet().stream().forEach(m -> {
            Campaign campaign = campaigns.stream().filter(c -> c.getId() == m.getKey()).findFirst().orElse(null);
            if (campaign == null) {
                return;
            }

            final OptimizationProps props = campaign.getOptimizationProps();
            final Map<Integer, Integer> blackList = campaign.getBlackList();

            m.getValue().entrySet().forEach(publisherEvents -> {
                long cntSource = publisherEvents.getValue().stream().filter(event -> event.getType().equals(props.getSourceEvent())).count();
                long cntMeasure = publisherEvents.getValue().stream().filter(event -> event.getType().equals(props.getMeasuredEvent())).count();
                long ratio = 100 * cntMeasure / cntSource;

                boolean pass = cntSource >= props.getThreshold() && ratio >= props.getRatioThreshold();
                int publisherId = publisherEvents.getKey();

                logger.debug(publisherId + " = " + cntSource + "%" + cntMeasure + "|" + ratio + "=" + pass);
                if (pass && blackList.containsKey(publisherId)) {
                    blackList.remove(publisherId);
                } else if (!pass) {
                    blackList.put(publisherId, publisherId);
                }
            });
        });
        logger.info("Finished OptimizationJob");
    }
}