package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "participants")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private short age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "host")
    private Set<EventEntity> hostedEvents;

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> participatedEvents;
}
