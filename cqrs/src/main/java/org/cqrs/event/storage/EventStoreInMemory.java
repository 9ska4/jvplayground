package org.cqrs.event.storage;

import org.cqrs.event.Event;
import org.cqrs.event.EventStore;

import java.util.ArrayList;
import java.util.List;

public class EventStoreInMemory implements EventStore {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void saveEvent(Event event) {
        events.add(event);
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}