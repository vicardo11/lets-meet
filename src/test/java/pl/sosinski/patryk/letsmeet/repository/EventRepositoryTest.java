package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventRepositoryTest {

    public static final String EVENT_ENTITY_NAME_JAVA = "Szkolenie Java";
    public static final String PARTICIPANT_FIRST_NAME_PATRYK = "Patryk";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    void given_when_then() {
        //Given
        EventEntity eventEntity = new EventEntity();
        ParticipantEntity participantEntity = new ParticipantEntity();
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);

        eventEntity.setName(EVENT_ENTITY_NAME_JAVA);
        eventEntity.setHost(savedParticipantEntity);

        //When
        EventEntity savedEventEntity = eventRepository.save(eventEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedEventEntity, "EventEntity is null"),
                () -> assertNotNull(savedEventEntity.getId(), "EventEntity.id is null")
        );
    }

    @Test
    void given_when_then1() {
        //Given
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setFirstName(PARTICIPANT_FIRST_NAME_PATRYK);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setName(EVENT_ENTITY_NAME_JAVA);


        //When
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        eventEntity.setHost(savedParticipantEntity);
        EventEntity savedEventEntity = eventRepository.save(eventEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedEventEntity, "EventEntity is null"),
                () -> assertNotNull(savedEventEntity.getId(), "EventEntity.id is null")
        );
    }

}