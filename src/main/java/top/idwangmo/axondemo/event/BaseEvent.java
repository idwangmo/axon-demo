package top.idwangmo.axondemo.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseEvent<T> {

    public final T id;

}
