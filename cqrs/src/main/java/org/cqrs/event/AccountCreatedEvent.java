package org.cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // for jackson
@AllArgsConstructor
public class AccountCreatedEvent extends DatedEvent {
    private String owner;

    public AccountCreatedEvent(String aggregateId, String owner) {
        super(aggregateId);
        this.owner = owner;
    }

}