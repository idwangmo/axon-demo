package top.idwangmo.axondemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.idwangmo.axondemo.dto.AccountCreateDTO;
import top.idwangmo.axondemo.dto.MoneyCreditDTO;
import top.idwangmo.axondemo.dto.MoneyDebitDTO;
import top.idwangmo.axondemo.service.AccountCommandService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bank-accounts")
@AllArgsConstructor
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    @PostMapping
    public CompletableFuture<Object> createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        return accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping("/credits/{accountNumber}")
    public CompletableFuture<Object> creditMoneyToAccount(@PathVariable("accountNumber") String accountNumber,
                                                          @RequestBody MoneyCreditDTO moneyCreditDTO) {
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping("/debits/{accountNumber}")
    public CompletableFuture<Object> debitMoneyFromAccount(@PathVariable("accountNumber") String accountNumber,
                                                           @RequestBody MoneyDebitDTO moneyDebitDTO) {
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }

}
