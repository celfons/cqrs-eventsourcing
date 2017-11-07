package br.com.example.cqrseventsourcing.domain.integration;

public interface ExternalService {
    boolean customerHasSomeImpediments(String id);
}
