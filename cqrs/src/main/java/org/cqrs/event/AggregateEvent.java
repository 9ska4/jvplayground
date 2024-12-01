package org.cqrs.event;

import lombok.Getter;

@Getter
public abstract class AggregateEvent implements Event {
    private final String aggregateId;

    protected AggregateEvent(String aggregateId) {
        this.aggregateId = aggregateId;
    }

}