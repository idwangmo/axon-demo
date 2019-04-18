package top.idwangmo.axondemo.event;

import java.math.BigDecimal;

public class MoneyDebitedEvent extends BaseEvent<String> {

    public final BigDecimal debitAmount;

    public final String currency;

    public MoneyDebitedEvent(String id, BigDecimal debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
