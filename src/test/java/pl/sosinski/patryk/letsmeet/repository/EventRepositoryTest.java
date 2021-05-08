package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class EventRepositoryTest {

    public static final String EVENT_ENTITY_NAME_JAVA = "Szkolenie Java";
    public static final String PARTICIPANT_FIRST_NAME_PATRYK = "Patryk";
    public static final int EVENT_PARTICIPANTS_SIZE_2 = 2;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Test
    void givenEventEntityAndRepository_whenSave_thenSavedEntityNotNull() {
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
    void givenEventEntityAndParticipants_whenSave_thenParticipantsSizeEqualTwo() {
        //Given
        EventEntity eventEntity = new EventEntity();
        HashSet<ParticipantEntity> participants = new HashSet<>();

        ParticipantEntity participantEntity1 = new ParticipantEntity();
        ParticipantEntity participantEntity2 = new ParticipantEntity();

        eventEntity.setHost(participantEntity1);

        participants.add(participantEntity1);
        participants.add(participantEntity2);

        //When
        participantRepository.save(participantEntity1);
        participantRepository.save(participantEntity2);

        eventEntity.setParticipants(participants);
        EventEntity savedEventEntity = eventRepository.save(eventEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedEventEntity, "EventEntity is null"),
                () -> assertNotNull(savedEventEntity.getHost(), "EventEntity hasn't a host"),
                () -> assertEquals(savedEventEntity.getParticipants().size(), EVENT_PARTICIPANTS_SIZE_2,
                        "Participants isn't equal to " + EVENT_PARTICIPANTS_SIZE_2)
        );

    }

    @Test
    void givenEventEntityAndInterest_whenFindByInterestId_thenFoundEventListSizeEqualsOne() {
        //Given
        EventEntity eventEntity = new EventEntity();
        ParticipantEntity participantEntity = new ParticipantEntity();
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);

        InterestEntity interestEntity = new InterestEntity();
        interestEntity.setName("Programowanie");
        interestEntity.addEvent(eventEntity);

        eventEntity.setName(EVENT_ENTITY_NAME_JAVA);
        eventEntity.setHost(savedParticipantEntity);
        eventEntity.addInterest(interestEntity);

        EventEntity savedEventEntity = eventRepository.save(eventEntity);
        InterestEntity savedInterestEntity = interestRepository.save(interestEntity);

        System.out.println(savedEventEntity);
        System.out.println(savedInterestEntity);

        //When
        List<Long> longs = List.of(1L);
        List<EventEntity> byInterestsContains = eventRepository.findByInterestsIdIn(longs);
        System.out.println(byInterestsContains);

        //Then
        assertEquals(1, byInterestsContains.size());
    }

}
