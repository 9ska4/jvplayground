package org.cqrs;

import lombok.RequiredArgsConstructor;
import org.cqrs.command.*;
import org.cqrs.command.handler.CreateAccountHandler;
import org.cqrs.command.handler.DepositMoneyHandler;
import org.cqrs.command.handler.WithdrawMoneyHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class CommandController {

    private final CreateAccountHandler createAccountHandler;
    private final DepositMoneyHandler depositMoneyHandler;
    private final WithdrawMoneyHandler withdrawMoneyHandler;


    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountCommand command) {
        createAccountHandler.handle(command);
        return ResponseEntity.ok("Account created");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody DepositMoneyCommand command) {
        depositMoneyHandler.handle(command);
        return ResponseEntity.ok("Money deposited");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestBody WithdrawMoneyCommand command) {
        withdrawMoneyHandler.handle(command);
        return ResponseEntity.ok("Money withdrawn");
    }
}
