package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.ParticipantRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.ParticipantMapper;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTest {

    public static final int EVENT_MODELS_SIZE_1 = 1;
    public static final String EVENT_NAME_JAVA_SZKOLENIE = "Java szkolenie";
    public static final String PARTICIPANT_NAME_PATRYK = "Patryk";

    @Autowired
    private EventService eventService;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ParticipantMapper participantMapper;

    @Test
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        EventModel eventModel = EventModel.builder()
                .name(EVENT_NAME_JAVA_SZKOLENIE)
                .build();

        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setFirstName(PARTICIPANT_NAME_PATRYK);

        //When
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        ParticipantModel savedParticipantModel = participantMapper.from(savedParticipantEntity);
        eventModel.setHost(savedParticipantModel);
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
        EventModel eventModel = EventModel.builder()
                .name(EVENT_NAME_JAVA_SZKOLENIE)
                .build();

        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setFirstName(PARTICIPANT_NAME_PATRYK);

        //When
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        ParticipantModel savedParticipantModel = participantMapper.from(savedParticipantEntity);
        eventModel.setHost(savedParticipantModel);
        eventService.create(eventModel);
        List<EventModel> eventModels = eventService.list();

        //Then
        assertAll(
                () -> assertNotNull(eventModels, "EventModels is null"),
                () -> assertEquals(EVENT_MODELS_SIZE_1, eventModels.size(), "EventModels isn't empty")
        );


    }
}