package pl.sosinski.patryk.letsmeet.web.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.sosinski.patryk.letsmeet.service.ParticipantService;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.PARTICIPANTS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantControllerTest {

    public static final long PARTICIPANT_ID_1 = 1L;
    public static final String PARTICIPANT_FIRST_NAME_PATRYK = "Patryk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    @Test
    @WithMockUser(username = "name")
    void givenParticipantsUri_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(PARTICIPANTS_URI))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenParticipantsUriAndId_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(PARTICIPANTS_URI + "/" + PARTICIPANT_ID_1))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenParticipant_whenGet_thenParticipantFieldsCorrect() throws Exception {
        //Given
        ParticipantModel participantModel = ParticipantModel.builder()
                .id(PARTICIPANT_ID_1)
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();

        //When
        when(participantService.read(PARTICIPANT_ID_1)).thenReturn(participantModel);

        mockMvc.perform(get(PARTICIPANTS_URI + "/" + PARTICIPANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(PARTICIPANT_ID_1))
                .andExpect(jsonPath("$.firstName").value(PARTICIPANT_FIRST_NAME_PATRYK));
        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenParticipantUriAndParticipant_whenPost_thenStatusIsOk() throws Exception {
        //Given
        ParticipantModel participantModel = ParticipantModel.builder()
                .id(PARTICIPANT_ID_1)
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String participantAsString = objectMapper.writeValueAsString(participantModel);

        //When
        mockMvc.perform(post(PARTICIPANTS_URI)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(participantAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenParticipantsUriAndParticipant_whenPut_thenStatusIsOk() throws Exception {
        //Given
        ParticipantModel participantModel = ParticipantModel.builder()
                .id(PARTICIPANT_ID_1)
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String participantAsString = objectMapper.writeValueAsString(participantModel);

        //When
        mockMvc.perform(put(PARTICIPANTS_URI)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(participantAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }
}