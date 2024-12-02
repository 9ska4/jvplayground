package org.cqrs.query;

import org.cqrs.event.Event;
import org.cqrs.event.EventStore;
import org.cqrs.event.MoneyDepositedEvent;
import org.cqrs.event.MoneyWithdrawnEvent;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryService {

    private final EventStore eventStore;

    public QueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public double getCurrentBalance(String accountId) {
        return eventStore.getEventsByAggregateId(accountId).stream()
                .mapToDouble(event -> {
                    if (event instanceof MoneyDepositedEvent) {
                        return ((MoneyDepositedEvent) event).getAmount();
                    } else if (event instanceof MoneyWithdrawnEvent) {
                        return -((MoneyWithdrawnEvent) event).getAmount();
                    }
                    return 0;
                })
                .sum();
    }

    public List<Event> getTransactionHistory(String accountId) {
        return eventStore.getEventsByAggregateId(accountId);
    }
}
