package top.idwangmo.axondemo.command;

import java.math.BigDecimal;

public class CreateAccountCommand extends BaseCommand<String>{

    public final BigDecimal accountBalance;

    public final String currency;

    public CreateAccountCommand(String id, BigDecimal accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
