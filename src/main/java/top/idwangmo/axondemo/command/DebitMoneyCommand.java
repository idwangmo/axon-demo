package top.idwangmo.axondemo.command;

import java.math.BigDecimal;

public class DebitMoneyCommand extends BaseCommand<String> {

    public final BigDecimal debitAmount;

    public final String currency;

    public DebitMoneyCommand(String id, BigDecimal debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
