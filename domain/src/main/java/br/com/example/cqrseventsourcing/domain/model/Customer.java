package br.com.example.cqrseventsourcing.domain.model;

import br.com.example.cqrseventsourcing.domain.event.CustomerCreated;
import br.com.example.cqrseventsourcing.domain.event.CustomerInactivated;
import br.com.example.cqrseventsourcing.domain.exception.CustomerAlreadyInactivedException;
import br.com.example.cqrseventsourcing.domain.exception.CustomerCantBeInactivatedException;
import br.com.example.cqrseventsourcing.domain.integration.ExternalService;
import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.AggregateRoot;
import br.com.zup.eventsourcing.core.Event;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Customer extends AggregateRoot {
    private String name;
    private boolean active;
    private String inactivationReason;

    public Customer(AggregateId id, String name) {
        applyChange(new CustomerCreated(id, name, true));
    }

    public void inactivate(String reason, ExternalService externalService) {
        if (!active) {
            throw new CustomerAlreadyInactivedException();
        }
        boolean hasSomeImpediments = externalService.customerHasSomeImpediments(id.getValue());
        if (hasSomeImpediments) {
            throw new CustomerCantBeInactivatedException();
        }
        applyChange(new CustomerInactivated(id, reason));
    }

    @Override
    public void applyEvent(Event event) {
        if (event instanceof CustomerCreated) apply((CustomerCreated) event);
        if (event instanceof CustomerInactivated) apply((CustomerInactivated) event);
    }

    private void apply(CustomerCreated event) {
        this.id = event.getAggregateId();
        this.name = event.getName();
        this.active = event.isActive();
    }

    private void apply(CustomerInactivated event) {
        this.active = false;
        this.inactivationReason = event.getReason();
    }
}
