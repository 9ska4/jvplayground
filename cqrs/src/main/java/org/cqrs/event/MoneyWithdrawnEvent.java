package org.cqrs.event;

import lombok.Getter;

@Getter
public class MoneyWithdrawnEvent extends AggregateEvent {
    private final double amount;

    public MoneyWithdrawnEvent(String aggregateId, double amount) {
        super(aggregateId);
        this.amount = amount;
    }

}