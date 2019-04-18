package top.idwangmo.axondemo.service;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import top.idwangmo.axondemo.command.CreditMoneyCommand;
import top.idwangmo.axondemo.command.DebitMoneyCommand;
import top.idwangmo.axondemo.command.CreateAccountCommand;
import top.idwangmo.axondemo.dto.AccountCreateDTO;
import top.idwangmo.axondemo.dto.MoneyCreditDTO;
import top.idwangmo.axondemo.dto.MoneyDebitDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public CompletableFuture<Object> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    public CompletableFuture<Object> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    public CompletableFuture<Object> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
