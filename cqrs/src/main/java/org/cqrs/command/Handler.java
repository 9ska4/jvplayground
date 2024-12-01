package org.cqrs.command;

import org.cqrs.event.storage.EventStoreInMemory;
import org.cqrs.event.Event;
import org.cqrs.event.MoneyDepositedEvent;
import org.cqrs.event.MoneyWithdrawnEvent;

public class Handler {
    private final EventStoreInMemory store;

    public Handler(EventStoreInMemory store) {
        this.store = store;
    }

    public MoneyDepositedEvent handle(DepositMoneyCommand command) {
        MoneyDepositedEvent event = new MoneyDepositedEvent(
                command.getAccountId(),
                command.getAmount()
        );
        store.saveEvent(event);
        return event;
    }

    public MoneyWithdrawnEvent handle(WithdrawMoneyCommand command) {
        if(calculateBalance(command.getAccountId()) < command.getAmount()) {
            throw new IllegalArgumentException("Not enough money");
        }
        MoneyWithdrawnEvent event = new MoneyWithdrawnEvent(
                command.getAccountId(),
                command.getAmount()
        );
        store.saveEvent(event);
        return event;
    }

    public double calculateBalance(String accountId) {
        return store.getEvents()
                .stream()
                .filter(event -> event instanceof MoneyDepositedEvent || event instanceof MoneyWithdrawnEvent)
                .filter(event -> doesEventMatchAccount(event, accountId))
                .mapToDouble(this::getAmountWithSign)
                .sum();
    }

    private boolean doesEventMatchAccount(Event event, String accountId) {
        if (event instanceof MoneyDepositedEvent depositedEvent) {
            return depositedEvent.getAccountId().equals(accountId);
        } else if (event instanceof MoneyWithdrawnEvent withdrawnEvent) {
            return withdrawnEvent.getAccountId().equals(accountId);
        }
        return false;
    }

    private double getAmountWithSign(Event event) {
        if (event instanceof MoneyDepositedEvent depositedEvent) {
            return depositedEvent.getAmount();
        } else if (event instanceof MoneyWithdrawnEvent withdrawnEvent) {
            return -withdrawnEvent.getAmount();
        }
        return 0;
    }
}