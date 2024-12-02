package org.cqrs.command.handler;

import lombok.AllArgsConstructor;
import org.cqrs.command.CreateAccountCommand;
import org.cqrs.event.AccountCreatedEvent;
import org.cqrs.event.EventStore;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountHandler implements CommandHandler<CreateAccountCommand> {

    private final EventStore eventStore;


    @Override
    public void handle(CreateAccountCommand command) {
        if (!eventStore.getEventsByAggregateId(command.getAccountId()).isEmpty()) {
            throw new IllegalStateException("Account with ID " + command.getAccountId() + " already exists.");
        }

        AccountCreatedEvent event = new AccountCreatedEvent(command.getAccountId(), command.getOwner());

        eventStore.saveEvent(event);
    }
}
