package br.com.example.cqrseventsourcing.integration;

import br.com.example.cqrseventsourcing.domain.integration.ExternalService;
import org.springframework.stereotype.Component;

@Component
public class ExernalServiceImpl implements ExternalService {

    @Override
    public boolean customerHasSomeImpediments(String id) {
        return false;
    }
}
