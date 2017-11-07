package br.com.example.cqrseventsourcing.command;

import br.com.zup.eventsourcing.core.AggregateId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class InactivateCustomerCommand implements Command {
    private String customerId;
    private String reason;
}
