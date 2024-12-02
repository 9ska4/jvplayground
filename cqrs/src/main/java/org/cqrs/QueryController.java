package org.cqrs;

import org.cqrs.event.Event;
import org.cqrs.query.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/{id}/balance")
    public double getAccountBalance(@PathVariable String id) {
        return queryService.getCurrentBalance(id);
    }

    @GetMapping("/{id}/transactions")
    public List<Event> getTransactionHistory(@PathVariable String id) {
        return queryService.getTransactionHistory(id);
    }
}
