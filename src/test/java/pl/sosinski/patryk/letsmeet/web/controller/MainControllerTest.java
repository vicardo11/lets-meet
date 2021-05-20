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
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.LOGIN_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.LOGIN_VIEW;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenLoginUrl_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(LOGIN_URL))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenLoginUrl_whenGet_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(LOGIN_URL))
                .andDo(print())
                .andExpect(view().name(LOGIN_VIEW));
        //Then
    }
}