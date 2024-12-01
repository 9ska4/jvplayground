package org.cqrs.event;

import lombok.Getter;

@Getter
public class MoneyDepositedEvent extends AggregateEvent {
    private final double amount;

    public MoneyDepositedEvent(String aggregateId, double amount) {
        super(aggregateId);
        this.amount = amount;
    }

}