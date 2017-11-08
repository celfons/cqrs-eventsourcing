package br.com.example.cqrseventsourcing.controller;

import br.com.example.cqrseventsourcing.CommandBus;
import br.com.example.cqrseventsourcing.api.CreateCustomerTO;
import br.com.example.cqrseventsourcing.api.CustomerApi;
import br.com.example.cqrseventsourcing.api.CustomerTO;
import br.com.example.cqrseventsourcing.api.InactivateCustomerTO;
import br.com.example.cqrseventsourcing.command.CreateCustomerCommand;
import br.com.example.cqrseventsourcing.command.InactivateCustomerCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CustomerController implements CustomerApi {

    private final CommandBus commandBus;

    public CustomerController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public CustomerTO create(@RequestBody CreateCustomerTO createCustomerTO) {
        String id = UUID.randomUUID().toString();
        commandBus.send(new CreateCustomerCommand(id, createCustomerTO.getName()));
        return new CustomerTO(id);
    }

    @Override
    public CustomerTO inactivate(@PathVariable String customerId, @RequestBody InactivateCustomerTO inactivateCustomerTO) {
        commandBus.send(new InactivateCustomerCommand(customerId, inactivateCustomerTO.getReason()));
        return new CustomerTO(customerId);
    }

}
