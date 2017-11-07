package br.com.example.cqrseventsourcing.domain.event;

import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.Event;
import br.com.zup.eventsourcing.core.EventID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class CustomerCreated extends Event {
    private AggregateId aggregateId;
    private String name;
    private boolean active;
}
