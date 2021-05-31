package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {

    private Long id;
    private String name;
    private ParticipantModel host;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTime;
    private String url;
    private int durationInMinutes;
    @EqualsAndHashCode.Exclude
    @Singular
    private List<ParticipantModel> participants = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<EventCategoryModel> categories = new HashSet<>();

    public void addEventCategory(EventCategoryModel eventCategory) {
        categories.add(eventCategory);
    }

    public void removeParticipant(ParticipantModel participantModel) {
        participants.remove(participantModel);
        participantModel.removeParticipatedEvent(this);
    }

    public void addParticipant(ParticipantModel participantModel) {
        participants.add(participantModel);
        participantModel.addParticipatedEvent(this);
    }
}
