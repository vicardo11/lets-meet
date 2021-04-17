package pl.sosinski.patryk.letsmeet.web.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.sosinski.patryk.letsmeet.service.InterestService;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.INTERESTS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class InterestControllerTest {

    public static final long INTEREST_ID_1 = 1L;
    public static final String INTEREST_NAME_SPORT = "Sport";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterestService interestService;

    @Test
    void givenInterestUriAndInterestId_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(INTERESTS_URI + "/" + INTEREST_ID_1))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenInterestUri_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(INTERESTS_URI))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenInterestUri_whenPost_thenStatusIsOk() throws Exception {
        //Given
        InterestModel interestModel = new InterestModel();
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(interestModel);

        //When
        mockMvc.perform(post(INTERESTS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void givenInterestUri_whenPut_thenStatusIsOk() throws Exception {
        //Given
        InterestModel interestModel = InterestModel.builder()
                .id(INTEREST_ID_1)
                .name(INTEREST_NAME_SPORT)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String interestModelAsString = objectMapper.writeValueAsString(interestModel);

        //When
        mockMvc.perform(put(INTERESTS_URI + "/" + INTEREST_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(interestModelAsString))
                .andDo(print())
                .andExpect(status().isOk());
        //Then
    }
}