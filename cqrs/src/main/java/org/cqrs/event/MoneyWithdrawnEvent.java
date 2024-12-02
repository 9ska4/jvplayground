package org.cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyWithdrawnEvent extends AggregateEvent {
    private double amount;

    public MoneyWithdrawnEvent(String aggregateId, double amount) {
        super(aggregateId);
        this.amount = amount;
    }

}