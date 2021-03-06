package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import static org.junit.jupiter.api.Assertions.*;


class ParticipantMapperTest {

    public static final long PARTICIPANT_ID_1 = 1L;
    public static final String PARTICIPANT_FIRSTNAME_PATRYK = "Patryk";
    public static final String PARTICIPANT_LASTNAME_SOSINSKI = "Sosiński";

    @Test
    void givenMapper_whenFrom_thenModelEquals() {
        //Given
        ParticipantMapper participantMapper = new ParticipantMapper();
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setId(PARTICIPANT_ID_1);
        participantEntity.setFirstName(PARTICIPANT_FIRSTNAME_PATRYK);
        participantEntity.setLastName(PARTICIPANT_LASTNAME_SOSINSKI);

        //When
        ParticipantModel participantModel = participantMapper.from(participantEntity);

        //Then
        assertAll(
                () -> assertNotNull(participantModel, "ParticipantModel is null"),
                () -> assertNotNull(participantModel.getId(), "ParticipantModel.id is null"),
                () -> assertNotNull(participantModel.getFirstName(), "ParticipantModel.firstName is null"),
                () -> assertNotNull(participantModel.getLastName(), "ParticipantModel.lastName is null")
        );
    }

    @Test
    void givenMapper_whenFrom_thenEntityEquals() {
        //Given
        ParticipantMapper participantMapper = new ParticipantMapper();
        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setId(PARTICIPANT_ID_1);
        participantModel.setFirstName(PARTICIPANT_FIRSTNAME_PATRYK);
        participantModel.setLastName(PARTICIPANT_LASTNAME_SOSINSKI);

        //When
        ParticipantEntity participantEntity = participantMapper.from(participantModel);

        //Then
        assertAll(
                () -> assertNotNull(participantEntity, "ParticipantEntity is null"),
                () -> assertNotNull(participantEntity.getId(), "ParticipantEntity.id is null"),
                () -> assertNotNull(participantEntity.getFirstName(), "ParticipantEntity.firstName is null"),
                () -> assertNotNull(participantEntity.getLastName(), "ParticipantEntity.lastName is null")
        );
    }
}