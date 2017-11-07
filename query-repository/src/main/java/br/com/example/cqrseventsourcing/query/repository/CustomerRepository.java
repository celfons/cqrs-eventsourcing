package br.com.example.cqrseventsourcing.query.repository;

import br.com.example.cqrseventsourcing.query.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
