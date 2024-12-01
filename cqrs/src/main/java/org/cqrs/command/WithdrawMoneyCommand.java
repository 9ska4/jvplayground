package org.cqrs.command;

public class WithdrawMoneyCommand implements Command {
    private final String accountId;
    private final double amount;

    public WithdrawMoneyCommand(String accountId, double amount) {
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