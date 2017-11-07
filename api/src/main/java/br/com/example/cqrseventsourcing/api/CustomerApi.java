package br.com.example.cqrseventsourcing.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface CustomerApi {

    @ResponseStatus(CREATED)
    @PostMapping(path = "/customers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    CustomerTO create(@RequestBody CreateCustomerTO command);

    @ResponseStatus(OK)
    @PostMapping(path = "/customers/{customerId}/inactivate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    CustomerTO inactivate(@PathVariable String customerId, @RequestBody InactivateCustomerTO command);
}
