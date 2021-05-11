package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PARTICIPANTS")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private short age;

    private String email;

    private String password;

    @OneToMany(mappedBy = "host")
    private Set<EventEntity> hostedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> participatedEvents = new HashSet<>();

    public ParticipantEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<EventEntity> getHostedEvents() {
        return hostedEvents;
    }

    public void setHostedEvents(Set<EventEntity> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }

    public Set<EventEntity> getParticipatedEvents() {
        return participatedEvents;
    }

    public void setParticipatedEvents(Set<EventEntity> participatedEvents) {
        this.participatedEvents = participatedEvents;
    }

    public void addParticipatedEvent(EventEntity eventEntity) {
        participatedEvents.add(eventEntity);
    }

    @Override
    public String toString() {
        return "ParticipantEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
