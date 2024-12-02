package org.cqrs.command.handler;

import lombok.AllArgsConstructor;
import org.cqrs.command.DepositMoneyCommand;
import org.cqrs.event.EventStore;
import org.cqrs.event.MoneyDepositedEvent;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepositMoneyHandler implements CommandHandler<DepositMoneyCommand> {

    private final EventStore eventStore;

    @Override
    public void handle(DepositMoneyCommand command) {
        if (eventStore.getEventsByAggregateId(command.getAccountId()).isEmpty()) {
            throw new IllegalStateException("Account does not exist.");
        }

        MoneyDepositedEvent event = new MoneyDepositedEvent(command.getAccountId(), command.getAmount());

        eventStore.saveEvent(event);
    }
}
