package org.cqrs.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class AggregateEvent implements Event {
    private String aggregateId;

    protected AggregateEvent(String aggregateId) {
        this.aggregateId = aggregateId;
    }

}
