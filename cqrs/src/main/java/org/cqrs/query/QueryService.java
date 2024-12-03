package org.cqrs.query;

import lombok.AllArgsConstructor;
import org.cqrs.event.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QueryService {

    private final EventStore eventStore;

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
        return eventStore.getEventsByAggregateId(accountId).stream()
                .map(event -> {
                    if (event instanceof MoneyDepositedEvent) {
                        return new MoneyDepositedEvent(
                                ((MoneyDepositedEvent) event).getAggregateId(),
                                ((MoneyDepositedEvent) event).getAmount());
                    } else if (event instanceof MoneyWithdrawnEvent) {
                        return new MoneyWithdrawnEvent(
                                ((MoneyWithdrawnEvent) event).getAggregateId(),
                                ((MoneyWithdrawnEvent) event).getAmount() * (-1));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Event> getFilteredTransactionHistory(String accountId, LocalDate startDate, LocalDate endDate, String eventType) {
        return eventStore.getEventsByAggregateId(accountId).stream()
                .filter(event -> event instanceof AggregateEvent)
                .filter(event -> eventType == null || event.getClass().getSimpleName().equals(eventType))
                .filter(event -> {
                    if (event instanceof DatedEvent) {
                        LocalDate eventDate = ((DatedEvent) event).getTimestamp().toLocalDate();
                        return !eventDate.isBefore(startDate) && !eventDate.isAfter(endDate);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

}
