package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "interests")
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "events_interests",
                joinColumns = {@JoinColumn(name = "interest_id")},
                inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private Set<EventEntity> events;
}
