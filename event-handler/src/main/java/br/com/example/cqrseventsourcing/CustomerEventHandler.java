package br.com.example.cqrseventsourcing;

import br.com.example.cqrseventsourcing.domain.event.CustomerCreated;
import br.com.example.cqrseventsourcing.domain.event.CustomerInactivated;
import br.com.example.cqrseventsourcing.query.model.Customer;
import br.com.example.cqrseventsourcing.query.repository.CustomerRepository;
import br.com.zup.eventsourcing.core.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CustomerEventHandler implements EventHandler {
    private final CustomerRepository customerRepository;

    public CustomerEventHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public void handle(AggregateId aggregateId, Event event, MetaData metaData, AggregateVersion aggregateVersion) {
        if (event instanceof CustomerCreated) handle(aggregateId, (CustomerCreated) event);
        if (event instanceof CustomerInactivated) handle(aggregateId, (CustomerInactivated) event);
    }

    private void handle(AggregateId aggregateId, CustomerCreated event) {
        Customer customer = Customer.builder()
                .id(aggregateId.getValue())
                .name(event.getName())
                .activated(event.isActive())
                .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                .updatedAt(LocalDateTime.now(ZoneId.of("UTC")))
                .build();
        customerRepository.save(customer);
    }

    private void handle(AggregateId aggregateId, CustomerInactivated event) {
        Customer customer = customerRepository.getOne(aggregateId.getValue());
        customer.setActivated(false);
        customer.setInactivationReason(event.getReason());
        customerRepository.save(customer);
    }
}
