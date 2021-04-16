package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantModel {

    private Long id;
    private String firstName;
    private String lastName;
    private short age;
    private String email;
    private String password;
    private Set<EventModel> hostedEvents;
    private Set<EventModel> participatedEvents;
}
