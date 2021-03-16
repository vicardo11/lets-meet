package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sosinski.patryk.letsmeet.repository.NotificationRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.NotificationMapper;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceMockitoMockTest {

    private static final Long NOTIFICATION_ID_1 = 1L;
    private static final String NOTIFICATION_DESCRIPTION_MORNING_YOGA = "Morning Yoga";

    @Spy
    @InjectMocks
    private NotificationService notificationService;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private NotificationMapper notificationMapper;

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
        verify(notificationService, times(2)).create(inputNotificationModel);
        verify(notificationMapper, times(2)).from(inputNotificationModel);

        assertAll(
                () -> assertNotNull(createdNotificationModel, "Created NotificationModel is null"),
                () -> assertEquals(NOTIFICATION_ID_1, createdNotificationModel.getId(), "Created NotificationModel ID is null")
        );
    }
}
