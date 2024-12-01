package org.cqrs.event;

public class MoneyWithdrawnEvent implements Event {
    private final String accountId;
    private final double amount;

    public MoneyWithdrawnEvent(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }
}