package org.cqrs;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExampleController {
    private final JsonService jsonService;

    @GetMapping("/test")
    public String testSerialization() {
        Example example = new Example("Test", 123);
        return jsonService.toJson(example);
    }
}

record Example (String name, Integer age) {}