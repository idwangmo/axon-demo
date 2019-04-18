package top.idwangmo.axondemo.command;

import lombok.AllArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    public final T id;

}
