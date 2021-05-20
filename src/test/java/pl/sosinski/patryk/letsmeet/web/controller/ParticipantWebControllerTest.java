package pl.sosinski.patryk.letsmeet.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.PARTICIPANTS_REGISTRATION_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.REGISTRATION_VIEW;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenParticipantsRegistrationUrl_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(PARTICIPANTS_REGISTRATION_URL))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenParticipantsRegistrationUrl_whenGet_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(PARTICIPANTS_REGISTRATION_URL))
                .andDo(print())
                .andExpect(view().name(REGISTRATION_VIEW));
        //Then
    }
}