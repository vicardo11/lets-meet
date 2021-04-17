package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void givenParticipantEntity_whenSave_thenEntityNotNull() {
        //Given
        ParticipantEntity participantEntity = new ParticipantEntity();

        //When
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedParticipantEntity, "SavedParticipantEntity is null"),
                () -> assertNotNull(savedParticipantEntity.getId(), "SavedParticipantEntity.id is null")
        );
    }
}