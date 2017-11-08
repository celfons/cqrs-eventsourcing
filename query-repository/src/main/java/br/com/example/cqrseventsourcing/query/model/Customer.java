package br.com.example.cqrseventsourcing.query.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Customer {
    @Id
    private String id;
    private String name;
    @Setter
    private boolean activated;
    @Setter
    private String inactivationReason;
    private LocalDateTime createdAt;
    @Setter
    private LocalDateTime updatedAt;
}
