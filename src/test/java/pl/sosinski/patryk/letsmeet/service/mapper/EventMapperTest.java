package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventMapperTest {

    public static final long EVENT_ID_1 = 1L;
    public static final String EVENT_NAME_WYDARZENIE = "Wydarzenie";

    @Test
    void givenMapper_whenFromEntity_thenModelEquals() {
        //Given
        EventMapper eventMapper = new EventMapper();
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(EVENT_ID_1);
        eventEntity.setName(EVENT_NAME_WYDARZENIE);

        //When
        EventModel eventModel = eventMapper.from(eventEntity);

        //Then
        assertAll(
                () -> assertNotNull(eventModel, "EventModel is null"),
                () -> assertNotNull(eventModel.getId(), "EventModel.id is null"),
                () -> assertNotNull(eventModel.getName(), "EventModel.name is null")
        );
    }

    @Test
    void givenMapper_whenFromModel_thenEntityEquals() {
        //Given
        EventMapper eventMapper = new EventMapper();
        EventModel eventModel = new EventModel();
        ParticipantModel participantModel = new ParticipantModel();
        eventModel.setId(EVENT_ID_1);
        eventModel.setName(EVENT_NAME_WYDARZENIE);
        eventModel.setHost(participantModel);

        //When
        EventEntity eventEntity = eventMapper.from(eventModel);

        //Then
        assertAll(
                () -> assertNotNull(eventEntity, "EventEntity is null"),
                () -> assertNotNull(eventEntity.getId(), "EventEntity.id is null"),
                () -> assertNotNull(eventEntity.getName(), "EventEntity.name is null"),
                () -> assertNotNull(eventEntity.getHost(), "EventEntity.host is null")
        );
    }

    @Test
    void givenMapperAndDateTime_whenFromModel_thenEntityEquals() {
        //Given
        EventMapper eventMapper = new EventMapper();
        EventModel eventModel = new EventModel();
        ParticipantModel participantModel = new ParticipantModel();
        LocalDateTime now = LocalDateTime.now();
        eventModel.setId(EVENT_ID_1);
        eventModel.setHost(participantModel);
        eventModel.setDateTime(now);

        //When
        EventEntity eventEntity = eventMapper.from(eventModel);

        //Then
        assertAll(
                () -> assertNotNull(eventEntity, "EventEntity is null"),
                () -> assertNotNull(eventEntity.getId(), "EventEntity.id is null"),
                () -> assertNotNull(eventEntity.getDateTime(), "EventEntity.dateTime is null"),
                () -> assertNotNull(eventEntity.getHost(), "EventEntity.host is null")
        );
    }

    @Test
    void givenMapperAndDateTime_whenFromEntity_thenModelEquals() {
        //Given
        EventMapper eventMapper = new EventMapper();
        EventEntity eventEntity = new EventEntity();
        ParticipantEntity participantEntity = new ParticipantEntity();

        LocalDateTime now = LocalDateTime.now();
        eventEntity.setId(EVENT_ID_1);
        eventEntity.setHost(participantEntity);
        eventEntity.setDateTime(now);

        //When
        EventModel eventModel = eventMapper.from(eventEntity);

        //Then
        assertAll(
                () -> assertNotNull(eventModel, "EventModel is null"),
                () -> assertNotNull(eventModel.getId(), "EventModel.id is null"),
                () -> assertNotNull(eventModel.getDateTime(), "EventModel.dateTime is null"),
                () -> assertNotNull(eventModel.getHost(), "EventModel.host is null")
        );
    }
}