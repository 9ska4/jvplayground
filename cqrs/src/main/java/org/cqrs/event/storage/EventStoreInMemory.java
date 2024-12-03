package org.cqrs.event.storage;

import org.cqrs.event.AggregateEvent;
import org.cqrs.event.Event;
import org.cqrs.event.EventStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class EventStoreInMemory implements EventStore {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void saveEvent(AggregateEvent event) {
        events.add(event);
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    @Override
    public List<Event> getEventsByAggregateId(String aggregateId) {
        return events.stream()
                .filter(event -> event instanceof AggregateEvent)
                .map(event -> (AggregateEvent) event)
                .filter(event -> event.getAggregateId().equals(aggregateId))
                .collect(Collectors.toList());
    }
}