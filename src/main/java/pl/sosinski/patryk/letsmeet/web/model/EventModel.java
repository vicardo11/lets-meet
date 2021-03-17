package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
