package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationServiceSpringMockTest {

    private static final Long NOTIFICATION_ID_1 = 1L;
    private static final String NOTIFICATION_DESCRIPTION_MORNING_YOGA = "Morning Yoga";

    @MockBean
    private NotificationService notificationService;

    @Test
    void given_when_then() {
        // given
        NotificationModel inputNotificationModel = NotificationModel.builder()
                .description(NOTIFICATION_DESCRIPTION_MORNING_YOGA)
                .build();
        NotificationModel outputNotificationModel = NotificationModel.builder()
                .id(NOTIFICATION_ID_1)
                .description(NOTIFICATION_DESCRIPTION_MORNING_YOGA)
                .build();

        // when
        when(notificationService.create(inputNotificationModel)).thenReturn(outputNotificationModel);
        NotificationModel createdNotificationModel = notificationService.create(inputNotificationModel);

        // then
        verify(notificationService).create(inputNotificationModel);
        assertAll(
                () -> assertNotNull(createdNotificationModel, "Created NotificationModel is null"),
                () -> assertEquals(NOTIFICATION_ID_1, createdNotificationModel.getId(), "Created NotificationModel ID is null")
        );
    }
}