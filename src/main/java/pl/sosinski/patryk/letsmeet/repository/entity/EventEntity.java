package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private ParticipantEntity host;

    @ManyToMany
    @JoinTable(name = "participants_events",
                joinColumns = {@JoinColumn(name = "event_id")},
                inverseJoinColumns = {@JoinColumn(name = "participant_id")})
    private Set<ParticipantEntity> participants;

    @ManyToMany(mappedBy = "events")
    private Set<InterestEntity> interests;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "url")
    private String url;

    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
}
