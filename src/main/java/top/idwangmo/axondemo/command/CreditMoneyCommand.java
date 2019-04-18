package top.idwangmo.axondemo.command;

import java.math.BigDecimal;

public class CreditMoneyCommand extends BaseCommand<String> {

    public final BigDecimal creditAmount;

    public final String currency;

    public CreditMoneyCommand(String id, BigDecimal creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
