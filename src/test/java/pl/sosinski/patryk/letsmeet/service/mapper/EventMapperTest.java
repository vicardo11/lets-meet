package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    public static final long EVENT_ID_1 = 1L;
    public static final String EVENT_NAME_WYDARZENIE = "Wydarzenie";

    @Test
    void givenMapper_whenFrom_thenEntityEquals() {
        //Given
        EventMapper eventMapper = new EventMapper();
        EventModel eventModel = new EventModel();
        eventModel.setId(EVENT_ID_1);
        eventModel.setName(EVENT_NAME_WYDARZENIE);

        //When
        EventEntity eventEntity = eventMapper.from(eventModel);

        //Then
        assertAll(
                () -> assertNotNull(eventEntity, "EventEntity is null"),
                () -> assertNotNull(eventEntity.getId(), "EventEntity.id is null"),
                () -> assertNotNull(eventEntity.getName(), "EventEntity.name is null")
        );
    }

    @Test
    void givenMapper_whenFrom_thenModelEquals() {
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
    void givenMapper_whenFrom_thenEntityEquals1() {
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


}