package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime dateTime;
    private String url;
    private int durationInMinutes;
    private List<ParticipantModel> participants;
    private Set<InterestModel> interests;
}
