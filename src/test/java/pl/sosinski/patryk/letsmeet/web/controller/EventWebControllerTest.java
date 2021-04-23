package pl.sosinski.patryk.letsmeet.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_VIEW;

@SpringBootTest
@AutoConfigureMockMvc
class EventWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenEventsUri_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenEventsUri_whenGet_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(view().name(EVENTS_VIEW))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenEventsUri_whenGet_thenAttributeExists() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(model().attributeExists(EVENTS_ATTRIBUTE))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenEventsUri_whenGet_thenContains() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(content().string(containsString("Events list")))
                .andExpect(status().isOk());

        //Then
    }

}