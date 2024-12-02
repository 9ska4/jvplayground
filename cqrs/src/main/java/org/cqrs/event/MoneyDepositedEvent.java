package org.cqrs.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDepositedEvent extends AggregateEvent {
    private double amount;

    public MoneyDepositedEvent(String aggregateId, double amount) {
        super(aggregateId);
        this.amount = amount;
    }

}