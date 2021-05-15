package pl.sosinski.patryk.letsmeet.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sosinski.patryk.letsmeet.service.EventCategoryService;
import pl.sosinski.patryk.letsmeet.service.EventService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.ADD_EVENT_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.ADD_EVENT_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_CATEGORIES_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.PARTICIPANTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.SEARCH_EVENT_BY_CATEGORY_URL;

@SpringBootTest
@AutoConfigureMockMvc
class EventWebControllerTest {

    public static final String CONTENT_STRING_EVENTS_LIST = "Events list";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventCategoryService eventCategoryService;
    @MockBean
    private EventService eventService;

    @Test
    void givenEventsUrl_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenEventsUrl_whenGet_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(view().name(EVENTS_VIEW))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenEventsUrl_whenGet_thenEventsAttributeExists() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(model().attributeExists(EVENTS_ATTRIBUTE))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenEventsUrl_whenGet_thenContentContainsEventsListString() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URL))
                .andDo(print())
                .andExpect(content().string(containsString(CONTENT_STRING_EVENTS_LIST)))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(ADD_EVENT_URL))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenAddEventUrl_whenGet_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(ADD_EVENT_URL))
                .andDo(print())
                .andExpect(view().name(ADD_EVENT_VIEW))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGet_thenParticipantsAttributeExists() throws Exception {
        //Given

        //When
        mockMvc.perform(get(ADD_EVENT_URL))
                .andDo(print())
                .andExpect(model().attributeExists(PARTICIPANTS_ATTRIBUTE))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGet_thenEventAttributeExists() throws Exception {
        //Given

        //When
        mockMvc.perform(get(ADD_EVENT_URL))
                .andDo(print())
                .andExpect(model().attributeExists(EVENT_ATTRIBUTE))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGet_thenEventCategoriesAttributeExists() throws Exception {
        //Given

        //When
        mockMvc.perform(get(ADD_EVENT_URL))
                .andDo(print())
                .andExpect(model().attributeExists(EVENT_CATEGORIES_ATTRIBUTE))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGetByEventCategory_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(SEARCH_EVENT_BY_CATEGORY_URL)
                .param("eventCategoryId", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void givenAddEventUrl_whenGetByEventCategory_thenViewNameCorrect() throws Exception {
        //Given

        //When
        mockMvc.perform(get(SEARCH_EVENT_BY_CATEGORY_URL)
                .param("eventCategoryId", "1"))
                .andDo(print())
                .andExpect(view().name(EVENTS_VIEW))
                .andExpect(status().isOk());

        //Then
    }



}