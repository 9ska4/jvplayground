package org.cqrs.command;

import lombok.Data;

@Data
public class CreateAccountCommand {
    private String accountId;
    private String owner;
}
