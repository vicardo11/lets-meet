package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParticipantModel {

    private Long id;
    private String firstName;
    private String lastName;
    private short age;
    private String email;
    private String password;
    @Singular
    @EqualsAndHashCode.Exclude
    private final Set<EventModel> hostedEvents = new HashSet<>();
    @Singular
    @EqualsAndHashCode.Exclude
    private final Set<EventModel> participatedEvents = new HashSet<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void removeParticipatedEvent(EventModel eventModel) {
        participatedEvents.remove(eventModel);
    }

    public void addParticipatedEvent(EventModel eventModel) {
        participatedEvents.add(eventModel);
    }
}
