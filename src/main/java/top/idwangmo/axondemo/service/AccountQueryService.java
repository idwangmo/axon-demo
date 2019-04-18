package top.idwangmo.axondemo.service;

import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountQueryService {

    private final EventStore eventStore;

    public List<Object> listEventForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().filter(n -> n.getType().equals("AccountAggregate")).map(Message::getPayload).collect(Collectors.toList());
    }

}
