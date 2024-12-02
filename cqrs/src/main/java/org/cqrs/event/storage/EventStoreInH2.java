package org.cqrs.event.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cqrs.event.AggregateEvent;
import org.cqrs.event.Event;
import org.cqrs.event.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventStoreInH2 implements EventStore {
    private final EventRepository repository;
    private final ObjectMapper objectMapper;

    public EventStoreInH2(EventRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveEvent(AggregateEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            repository.save(new EventEntity(event.getAggregateId(), event.getClass().getSimpleName(), payload));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save event", e);
        }
    }

    @Override
    public List<Event> getEvents() {
        return repository.findAll().stream()
                .map(this::tryToCast)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsByAggregateId(String aggregateId) {
        return repository.findByAggregateId(aggregateId).stream()
                .map(this::tryToCast)
                .collect(Collectors.toList());
    }

    private Event tryToCast(EventEntity entity) {
        try {
            Class<?> eventType = Class.forName("org.cqrs.event." + entity.getType());
            return (Event) objectMapper.readValue(entity.getPayload(), eventType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load event", e);
        }
    }
}