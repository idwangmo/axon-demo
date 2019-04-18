package top.idwangmo.axondemo.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import top.idwangmo.axondemo.command.CreateAccountCommand;
import top.idwangmo.axondemo.command.CreditMoneyCommand;
import top.idwangmo.axondemo.command.DebitMoneyCommand;
import top.idwangmo.axondemo.event.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private BigDecimal accountBalance;

    private String currency;

    private String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance,
                createAccountCommand.currency));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        this.status = accountActivatedEvent.status;
    }

    @CommandHandler
    public void on(CreditMoneyCommand creditMoneyCommand) {
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount,
                creditMoneyCommand.currency));
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent moneyCreditedEvent) {

        if (this.accountBalance.compareTo(BigDecimal.ZERO) < 0 && (this.accountBalance.add(moneyCreditedEvent.creditAmount)).compareTo(BigDecimal.ZERO) >= 0) {
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, "ACTIVATED"));
        }

        this.accountBalance =  this.accountBalance.add(moneyCreditedEvent.creditAmount);
    }

    @CommandHandler
    public void on(DebitMoneyCommand debitMoneyCommand) {
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent moneyDebitedEvent) {

        if (this.accountBalance.compareTo(BigDecimal.ZERO) >= 0 && (this.accountBalance.subtract(moneyDebitedEvent.debitAmount).compareTo(BigDecimal.ZERO)) < 0) {
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, "HOLD"));
        }

        this.accountBalance = this.accountBalance.add(moneyDebitedEvent.debitAmount);
    }

    @EventSourcingHandler
    public void on(AccountHeldEvent accountHeldEvent) {
        this.status = accountHeldEvent.status;
    }


    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = "CREATED";

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, "ACTIVATED"));
    }

}
