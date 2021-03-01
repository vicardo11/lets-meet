package pl.sosinski.patryk.letsmeet.web.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventModel {

    private Long id;
    private String name;
    private ParticipantModel host;
    private LocalDateTime dateTime;
    private String url;
    private int durationInMinutes;
}
