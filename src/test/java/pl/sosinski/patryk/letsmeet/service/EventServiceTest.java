package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTest {

    public static final int EVENT_MODELS_SIZE_1 = 1;

    @Autowired
    private EventService eventService;

    @Test
    @Transactional
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        ParticipantModel participantModel = ParticipantModel.builder()
                .firstName("Patryk")
                .build();

        EventModel eventModel = EventModel.builder()
                .name("Java szkolenie")
                .host(participantModel)
                .build();

        //When
        EventModel createdEventModel = eventService.create(eventModel);

        //Then
        assertAll(
                () -> assertNotNull(createdEventModel, "CreatedEvent is null"),
                () -> assertNotNull(createdEventModel.getId(), "CreatedEvent.id is null")
        );
    }

    @Test
    void givenModelAndService_whenCreate_thenServiceListSizeOne() {
        //Given
        EventModel eventModel = new EventModel();

        //When
        eventService.create(eventModel);
        List<EventModel> eventModels = eventService.list();

        //Then
        assertAll(
                () -> assertNotNull(eventModels, "EventModels is null"),
                () -> assertEquals(EVENT_MODELS_SIZE_1, eventModels.size(), "EventModels isn't empty")
        );


    }
}