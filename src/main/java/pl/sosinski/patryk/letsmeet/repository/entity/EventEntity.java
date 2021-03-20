package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "host_id", nullable = false)
    private ParticipantEntity host;

//    @ManyToMany
//    @JoinTable(name = "participants_events",
//                joinColumns = {@JoinColumn(name = "event_id")},
//                inverseJoinColumns = {@JoinColumn(name = "participant_id")})
//    private Set<ParticipantEntity> participants;

//    @ManyToMany(mappedBy = "events")
//    private Set<InterestEntity> interests;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "url")
    private String url;

    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    public EventEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParticipantEntity getHost() {
        return host;
    }

    public void setHost(ParticipantEntity host) {
        this.host = host;
    }

//    public Set<ParticipantEntity> getParticipants() {
//        return participants;
//    }
//
//    public void setParticipants(Set<ParticipantEntity> participants) {
//        this.participants = participants;
//    }
//
//    public Set<InterestEntity> getInterests() {
//        return interests;
//    }
//
//    public void setInterests(Set<InterestEntity> interests) {
//        this.interests = interests;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", host=" + host +
                ", dateTime=" + dateTime +
                ", url='" + url + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
