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
import pl.sosinski.patryk.letsmeet.service.NotificationService;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

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
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.NOTIFICATIONS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

    public static final int NOTIFICATION_MODELS_SIZE_2 = 2;
    public static final long NOTIFICATION_ID_1 = 1L;
    public static final String NOTIFICATION_DESCRIPTION_EVENT_COMING = "Your event is coming";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    @WithMockUser(username = "name")
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
    @WithMockUser(username = "name")
    void givenUriNotifications_whenGet_thenNotificationModelsSizeEqualTwo() throws Exception {
        //Given
        List<NotificationModel> notificationModels = Arrays.asList(
                new NotificationModel(), new NotificationModel()
        );

        //When
        when(notificationService.list()).thenReturn(notificationModels);

        MvcResult mvcResult = mockMvc.perform(get(NOTIFICATIONS_URI))
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
    @WithMockUser(username = "name")
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
    @WithMockUser(username = "name")
    void givenUriNotificationsWithNotificationModel_whenPost_thenStatusIsOK() throws Exception {
        //Given
        NotificationModel notificationModel = new NotificationModel();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(notificationModel);

        //When
        mockMvc.perform(post(NOTIFICATIONS_URI)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    @WithMockUser(username = "name")
    void givenUriNotificationsAndNotificationModel_whenPost_thenCreatedNotificationModelNotNull() throws Exception {
        //Given
        NotificationModel notificationModel = NotificationModel.builder()
                .id(NOTIFICATION_ID_1)
                .description(NOTIFICATION_DESCRIPTION_EVENT_COMING)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(notificationModel);

        //When
        when(notificationService.create(notificationModel)).thenReturn(notificationModel);
        MvcResult mvcResult = mockMvc.perform(post(NOTIFICATIONS_URI)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        NotificationModel parsedNotificationModel = objectMapper.readValue(contentAsString, NotificationModel.class);
        //Then
        assertAll(
                () -> assertNotNull(parsedNotificationModel, "NotificationModel is null"),
                () -> assertNotNull(parsedNotificationModel.getId(), "NotificationModel.id is null")
        );

    }

    @Test
    @WithMockUser(username = "name")
    void givenUriNotificationsWithIdAndNotificationModel_whenPut_thenStatusIsOk() throws Exception {
        //Given
        NotificationModel notificationModel = NotificationModel.builder()
                .id(NOTIFICATION_ID_1)
                .description(NOTIFICATION_DESCRIPTION_EVENT_COMING)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(notificationModel);

        //When
        when(notificationService.update(notificationModel)).thenReturn(notificationModel);
        mockMvc.perform(put(NOTIFICATIONS_URI + "/" + NOTIFICATION_ID_1)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        //Then
    }

}