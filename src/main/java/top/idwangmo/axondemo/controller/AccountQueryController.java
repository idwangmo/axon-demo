package top.idwangmo.axondemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.idwangmo.axondemo.service.AccountQueryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("bank-accounts")
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable("accountNumber") String accountNumber) {
        return accountQueryService.listEventForAccount(accountNumber);
    }

}
