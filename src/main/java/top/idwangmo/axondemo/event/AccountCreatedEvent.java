package top.idwangmo.axondemo.event;

import java.math.BigDecimal;

public class AccountCreatedEvent extends BaseEvent<String> {
    public final BigDecimal accountBalance;

    public final String currency;

    public AccountCreatedEvent(String id, BigDecimal accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
