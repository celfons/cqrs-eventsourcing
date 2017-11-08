package br.com.example.cqrseventsourcing;

import br.com.example.cqrseventsourcing.domain.model.Customer;
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventSubscriber extends PersistentAggregateSubscriber<Customer> {

    public CustomerEventSubscriber(CustomerEventHandler eventHandler) {
        super("CustomerSubscriptionGroup", eventHandler);
    }
}
