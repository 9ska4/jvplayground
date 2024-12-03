package org.cqrs;

import org.cqrs.command.DepositMoneyCommand;
import org.cqrs.command.handler.Handler;
import org.cqrs.command.WithdrawMoneyCommand;
import org.cqrs.event.storage.EventStoreInMemory;

/**
 * Hello world!
 *
 */
@Deprecated
public class App 
{
    public static void main(String[] args) {
        EventStoreInMemory store = new EventStoreInMemory();
        Handler handler = new Handler(store);

        DepositMoneyCommand deposit = new DepositMoneyCommand("account1", 100);
        handler.handle(deposit);

        WithdrawMoneyCommand withdraw = new WithdrawMoneyCommand("account1", 55);
        handler.handle(withdraw);

        double balance = handler.calculateBalance("account1");
        System.out.println("Current balance: " + balance);
    }
}
