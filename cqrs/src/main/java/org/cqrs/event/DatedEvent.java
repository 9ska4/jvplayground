package org.cqrs.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public abstract class DatedEvent extends AggregateEvent {
    private LocalDateTime timestamp;

    protected DatedEvent(String aggregateId) {
        super(aggregateId);
        this.timestamp = LocalDateTime.now();
    }

    protected DatedEvent(String aggregateId, LocalDateTime timestamp) {
        super(aggregateId);
        this.timestamp = timestamp;
    }
}
