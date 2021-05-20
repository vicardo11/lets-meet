package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EVENT_CATEGORIES")
public class EventCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private Set<EventEntity> events = new HashSet<>();

    public EventCategoryEntity() {
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

    public Set<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }

    public void addEvent(EventEntity eventEntity){
        events.add(eventEntity);
    }

    @Override
    public String toString() {
        return "EventCategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
