package pl.sosinski.patryk.letsmeet.web.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENTS_URI;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.NOTIFICATIONS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    public static final int EVENT_MODELS_SIZE_2 = 2;
    public static final long EVENT_ID_1 = 1L;
    public static final String EVENT_NAME_JAVA_TRAINING = "Java training";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    @WithMockUser(username = "name")
    void givenEventsUri_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENTS_URI))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenEventsUriAndEventModels_whenGet_thenEventModelsSizeEqualToTwo() throws Exception {
        //Given
        List<EventModel> eventModels = Arrays.asList(
                new EventModel(), new EventModel()
        );

        //When
        when(eventService.list()).thenReturn(eventModels);
        MvcResult mvcResult = mockMvc.perform(get(EVENTS_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String contentAsString = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EventModel> parsedEventModels = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        //Then
        assertAll(
                () -> assertNotNull(parsedEventModels, "EventModels is empty"),
                () -> assertEquals(parsedEventModels.size(), EVENT_MODELS_SIZE_2, "EventModels is empty")
        );
    }

    @Test
    @WithMockUser(username = "name")
    void givenEventsUriAndEventId_whenGet_thenStatusIsOk() throws Exception {
        //Given
        EventModel eventModel = EventModel.builder()
                .id(EVENT_ID_1)
                .build();

        //When
        when(eventService.read(EVENT_ID_1)).thenReturn(eventModel);
        mockMvc.perform(get(EVENTS_URI + "/" + EVENT_ID_1))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenEventsUriAndEventId_whenGet_thenEventModelNotNull() throws Exception {
        //Given
        EventModel eventModel = EventModel.builder()
                .id(EVENT_ID_1)
                .build();

        //When
        when(eventService.read(EVENT_ID_1)).thenReturn(eventModel);
        MvcResult mvcResult = mockMvc.perform(get(EVENTS_URI + "/" + EVENT_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        EventModel parsedEventModel = objectMapper.readValue(contentAsString, EventModel.class);
        //Then
        assertAll(
                () -> assertNotNull(parsedEventModel, "EventModel is null"),
                () -> assertNotNull(parsedEventModel.getId(), "EventModel.id is null")
        );
    }

    @Test
    @WithMockUser(username = "name")
    void givenEventsUri_whenPost_thenStatusIsOk() throws Exception {
        //Given
        EventModel eventModel = EventModel.builder()
                .name(EVENT_NAME_JAVA_TRAINING)
                .id(EVENT_ID_1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(eventModel);

        //When
        mockMvc.perform(post(EVENTS_URI)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenEventsUri_whenPut_thenStatusIsOk() throws Exception {
        //Given
        EventModel eventModel = EventModel.builder()
                .id(EVENT_ID_1)
                .name(EVENT_NAME_JAVA_TRAINING)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(eventModel);

        //When
        mockMvc.perform(put(NOTIFICATIONS_URI + "/" + EVENT_ID_1)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then

    }

}