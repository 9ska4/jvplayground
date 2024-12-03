package org.cqrs;

import lombok.AllArgsConstructor;
import org.cqrs.event.Event;
import org.cqrs.query.QueryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class QueryController {

    private final QueryService queryService;

    @GetMapping("/{id}/balance")
    public double getAccountBalance(@PathVariable String id) {
        return queryService.getCurrentBalance(id);
    }

    @GetMapping("/{id}/transactions")
    public List<Event> getTransactionHistory(@PathVariable String id) {
        return queryService.getTransactionHistory(id);
    }

    @GetMapping("/{id}/transactions/date-range")
    public List<Event> getTransactionsByDateRange(
            @PathVariable String id,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return queryService.getFilteredTransactionHistory(id, start, end, null);
    }
}
