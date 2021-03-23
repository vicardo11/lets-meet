package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantServiceMockTest {

    public static final String PARTICIPANT_FIRST_NAME_PATRYK = "Patryk";
    public static final long PARTICIPANT_ID_1 = 1L;

    @MockBean
    private ParticipantService participantService;

    @Test
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        ParticipantModel inputParticipantModel = ParticipantModel.builder()
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();
        ParticipantModel outputParticipantModel = ParticipantModel.builder()
                .id(PARTICIPANT_ID_1)
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();

        //When
        when(participantService.create(inputParticipantModel)).thenReturn(outputParticipantModel);
        ParticipantModel createdParticipantModel = participantService.create(inputParticipantModel);

        //Then
        verify(participantService).create(inputParticipantModel);
        assertAll(
                () -> assertNotNull(createdParticipantModel, "ParticipantModel is null"),
                () -> assertNotNull(createdParticipantModel.getId(), "ParticipantModel.id is null")
        );
    }
}