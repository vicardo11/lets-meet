package pl.sosinski.patryk.letsmeet.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.sosinski.patryk.letsmeet.service.NotificationService;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.NOTIFICATIONS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

    public static final int NOTIFICATION_MODELS_SIZE_2 = 2;
    public static final long NOTIFICATION_ID_1 = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void givenUriNotifications_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(NOTIFICATIONS_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //Then
        //NOTE:Assert is made by andExpect() method
    }

    @Test
    void givenUriNotifications_whenGet_thenNotificationModelsSizeEqualTwo() throws Exception {
        //Given
        List<NotificationModel> notificationModels = Arrays.asList(
                new NotificationModel(), new NotificationModel()
        );

        //When
        when(notificationService.list()).thenReturn(notificationModels);

        MvcResult mvcResult = mockMvc.perform(get("/notifications"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String contentAsString = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<NotificationModel> parsedNotificationModels = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        //Then
        assertEquals(NOTIFICATION_MODELS_SIZE_2, parsedNotificationModels.size(),
                "NotificationModels isn't equal to " + NOTIFICATION_MODELS_SIZE_2);
    }

    @Test
    void givenUriNotificationsWithId_whenGet_thenStatusIsOk() throws Exception {
        //Given
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setId(NOTIFICATION_ID_1);

        //When
        when(notificationService.read(NOTIFICATION_ID_1)).thenReturn(notificationModel);
        MvcResult mvcResult = mockMvc.perform(get(NOTIFICATIONS_URI + "/" + NOTIFICATION_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationModel parsedNotificationModel = objectMapper.readValue(contentAsString, NotificationModel.class);

        //Then
        assertAll(
                () -> assertNotNull(parsedNotificationModel, "NotificationModel is null"),
                () -> assertNotNull(parsedNotificationModel.getId(), "NotificationModel Id is null")
        );
    }

    @Test
    void givenUriNotificationsWithIdAndNotificationModel_whenPut_thenStatusIsOk() throws Exception {
        //Given

        //When
        MvcResult mvcResult = mockMvc.perform(put(NOTIFICATIONS_URI + "/" + NOTIFICATION_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
    }

}