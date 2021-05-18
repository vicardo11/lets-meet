package pl.sosinski.patryk.letsmeet.web.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.sosinski.patryk.letsmeet.service.EventCategoryService;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_CATEGORY_URI;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class EventCategoryControllerTest {

    public static final long EVENT_CATEGORY_ID_1 = 1L;
    public static final String EVENT_CATEGORY_NAME_SPORT = "Sport";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventCategoryService eventCategoryService;

    @Test
    void givenEventCategoryUriAndEventCategoryId_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENT_CATEGORY_URI + "/" + EVENT_CATEGORY_ID_1))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenEventCategoryUri_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(EVENT_CATEGORY_URI))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenEventCategoryUri_whenPost_thenStatusIsOk() throws Exception {
        //Given
        EventCategoryModel eventCategoryModel = new EventCategoryModel();
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(eventCategoryModel);

        //When
        mockMvc.perform(post(EVENT_CATEGORY_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenEventCategoryUri_whenPut_thenStatusIsOk() throws Exception {
        //Given
        EventCategoryModel eventCategoryModel = EventCategoryModel.builder()
                .id(EVENT_CATEGORY_ID_1)
                .name(EVENT_CATEGORY_NAME_SPORT)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String interestModelAsString = objectMapper.writeValueAsString(eventCategoryModel);

        //When
        mockMvc.perform(put(EVENT_CATEGORY_URI + "/" + EVENT_CATEGORY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(interestModelAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }
}