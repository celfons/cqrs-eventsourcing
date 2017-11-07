package br.com.example.cqrseventsourcing.handler;

import br.com.example.cqrseventsourcing.command.InactivateCustomerCommand;
import br.com.example.cqrseventsourcing.command.repository.CustomerEventstoreRepository;
import br.com.example.cqrseventsourcing.domain.integration.ExternalService;
import br.com.example.cqrseventsourcing.domain.model.Customer;
import br.com.zup.eventsourcing.core.AggregateId;
import org.springframework.stereotype.Component;

import static br.com.zup.eventsourcing.core.Repository.OptimisticLock.ENABLED;

@Component
public class InactivateCustomerHandler implements Handler<InactivateCustomerCommand> {

    private final CustomerEventstoreRepository customerEventstoreRepository;
    private final ExternalService externalService;

    public InactivateCustomerHandler(CustomerEventstoreRepository customerEventstoreRepository, ExternalService externalService) {
        this.customerEventstoreRepository = customerEventstoreRepository;
        this.externalService = externalService;
    }

    @Override
    public void handle(InactivateCustomerCommand command) {
        Customer customer = customerEventstoreRepository.get(new AggregateId(command.getCustomerId()));
        customer.inactivate(command.getReason(), externalService);
        customerEventstoreRepository.save(customer, ENABLED);
    }

    @Override
    public Class<InactivateCustomerCommand> getCommandClass() {
        return InactivateCustomerCommand.class;
    }
}
