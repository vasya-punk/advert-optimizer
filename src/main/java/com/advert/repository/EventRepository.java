package com.advert.repository;

import com.advert.model.Event;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class EventRepository {
    Logger logger = Logger.getLogger(EventsDataSource.class);

    EventsDataSource ds = new EventsDataSource();

    public Set<Event> getEventsSince(int i) {
        logger.debug("getEventsSince");

        return ds.getEventsSince(i);
    }
}
