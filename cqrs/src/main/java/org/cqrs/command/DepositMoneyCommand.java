package org.cqrs.command;

public class DepositMoneyCommand implements Command {
    private final String accountId;
    private final double amount;

    public DepositMoneyCommand(String accountId, double amount) {
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
