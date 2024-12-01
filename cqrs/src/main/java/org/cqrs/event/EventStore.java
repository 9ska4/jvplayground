package org.cqrs.event;

import java.util.List;

public interface EventStore {

    void saveEvent(AggregateEvent event);
    List<Event> getEvents();
    List<Event> getEventsByAggregateId(String aggregateId);
}
