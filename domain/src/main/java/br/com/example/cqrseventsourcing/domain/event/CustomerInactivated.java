package br.com.example.cqrseventsourcing.domain.event;

import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class CustomerInactivated extends Event {
    private AggregateId aggregateId;
    private String reason;
}
