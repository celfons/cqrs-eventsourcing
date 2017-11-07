package br.com.example.cqrseventsourcing.command.repository;

import br.com.example.cqrseventsourcing.domain.model.Customer;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerEventstoreRepository extends EventStoreRepository<Customer> {
}
