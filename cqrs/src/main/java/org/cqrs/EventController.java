package org.cqrs;

import lombok.AllArgsConstructor;
import org.cqrs.event.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
@Deprecated
public class EventController {

    private final EventStore eventStore;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventStore.getEvents();
    }

    @GetMapping("/{aggregateId}")
    public List<Event> getEventsByAggregateId(@PathVariable String aggregateId) {
        return eventStore.getEventsByAggregateId(aggregateId);
    }

    @PostMapping
    public void postEvent(@RequestBody EventDto eventDto) {
        var event = mapToEvent(eventDto);
        eventStore.saveEvent(event);
    }

    private AggregateEvent mapToEvent(EventDto eventDto) {
        return switch (eventDto.type()) {
            case "MoneyDepositedEvent" -> new MoneyDepositedEvent(eventDto.aggregateId(), eventDto.amount());
            case "MoneyWithdrawnEvent" -> new MoneyWithdrawnEvent(eventDto.aggregateId(), eventDto.amount());
            default -> throw new IllegalArgumentException("Unsupported event type: " + eventDto.type());
        };
    }
    record EventDto (String type, String aggregateId, double amount) {}
}

