package br.com.example.cqrseventsourcing.handler;

import br.com.example.cqrseventsourcing.command.CreateCustomerCommand;
import br.com.example.cqrseventsourcing.command.repository.CustomerEventstoreRepository;
import br.com.example.cqrseventsourcing.domain.model.Customer;
import br.com.zup.eventsourcing.core.AggregateId;
import org.springframework.stereotype.Component;

import static br.com.zup.eventsourcing.core.Repository.OptimisticLock.ENABLED;

@Component
public class CreateCustomerHandler implements Handler<CreateCustomerCommand> {

    private final CustomerEventstoreRepository customerEventstoreRepository;

    public CreateCustomerHandler(CustomerEventstoreRepository customerEventstoreRepository) {
        this.customerEventstoreRepository = customerEventstoreRepository;
    }

    @Override
    public void handle(CreateCustomerCommand command) {
        Customer customer = new Customer(new AggregateId(command.getId()), command.getName());
        customerEventstoreRepository.save(customer, ENABLED);
    }

    @Override
    public Class<CreateCustomerCommand> getCommandClass() {
        return CreateCustomerCommand.class;
    }
}
