package com.advert.repository;

import com.advert.model.Event;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class EventsDataSource {

    private Set<Event> events = new HashSet<>();

    public Set<Event> getEventsSince(int i) {
        return events;
    }
}
