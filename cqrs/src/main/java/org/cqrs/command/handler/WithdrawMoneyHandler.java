package org.cqrs.command.handler;

import lombok.AllArgsConstructor;
import org.cqrs.command.WithdrawMoneyCommand;
import org.cqrs.event.EventStore;
import org.cqrs.event.MoneyDepositedEvent;
import org.cqrs.event.MoneyWithdrawnEvent;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WithdrawMoneyHandler implements CommandHandler<WithdrawMoneyCommand> {

    private final EventStore eventStore;

    @Override
    public void handle(WithdrawMoneyCommand command) {
        double balance = eventStore.getEventsByAggregateId(command.getAccountId())
                .stream()
                .mapToDouble(event -> {
                    if (event instanceof MoneyDepositedEvent) {
                        return ((MoneyDepositedEvent) event).getAmount();
                    } else if (event instanceof MoneyWithdrawnEvent) {
                        return -((MoneyWithdrawnEvent) event).getAmount();
                    }
                    return 0;
                })
                .sum();

        if (balance < command.getAmount()) {
            throw new IllegalStateException("Insufficient funds.");
        }

        MoneyWithdrawnEvent event = new MoneyWithdrawnEvent(command.getAccountId(), command.getAmount());

        eventStore.saveEvent(event);
    }
}
