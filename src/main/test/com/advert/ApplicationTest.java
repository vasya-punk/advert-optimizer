package com.advert;

import com.advert.jobs.OptimizationJob;
import com.advert.model.Campaign;
import com.advert.model.Event;
import com.advert.model.OptimizationProps;
import com.advert.repository.CampaignRepository;
import com.advert.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

    @InjectMocks
    private OptimizationJob job;

    @Mock
    CampaignRepository campaignRepository;

    @Mock
    EventRepository eventRepository;

    private Set<Event> events = new HashSet<>();
    private Set<Campaign> campaigns = new HashSet<>();

    @Before
    public void initDs(){
        events.add(new Event("install", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));
        events.add(new Event("install", 1, 22));

        campaigns.add(new Campaign(
            new OptimizationProps(3, "install", "purchase", 10),
            1,
            new HashMap<>()
        ));

    }

    @Test
    public void contextLoads() {
        // Basic integration test that shows the context starts up properly
        assertNotNull(job);
    }

    @Test
    public void testJob_publisherAddToBlackList(){
        Mockito.when(eventRepository.getEventsSince(ArgumentMatchers.anyInt())).thenReturn(events);
        Mockito.when(campaignRepository.getCampaigns()).thenReturn(campaigns);

        job.executeInternal();

        assertEquals(campaignRepository.getCampaigns().iterator().next().getBlackList().size(), 1);
    }

    private void addPurchaseEvents(){
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
        events.add(new Event("purchase", 1, 22));
    }

    @Test
    public void testJob_publisherRemoveFromBlackList(){
        addPurchaseEvents();

        Mockito.when(eventRepository.getEventsSince(ArgumentMatchers.anyInt())).thenReturn(events);
        Mockito.when(campaignRepository.getCampaigns()).thenReturn(campaigns);

        job.executeInternal();

        assertEquals(campaignRepository.getCampaigns().iterator().next().getBlackList().size(), 0);
    }
}