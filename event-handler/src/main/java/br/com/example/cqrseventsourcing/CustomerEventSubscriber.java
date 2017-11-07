package br.com.example.cqrseventsourcing;

import br.com.example.cqrseventsourcing.domain.model.Customer;
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventSubscriber extends PersistentAggregateSubscriber<Customer> implements InitializingBean {

    public CustomerEventSubscriber(CustomerEventHandler eventHandler) {
        super("CustomerSubscriptionGroup", eventHandler);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}
