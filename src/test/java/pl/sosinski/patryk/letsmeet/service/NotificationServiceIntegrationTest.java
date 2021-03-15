package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;
import pl.sosinski.patryk.letsmeet.core.exception.NotificationNotFoundException;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NotificationServiceIntegrationTest {

    public static final int NOTIFICATION_MODELS_SIZE_1 = 1;
    public static final long NOTIFICATION_ID_NEGATIVE_1 = -1L;
    public static final String NOTIFICATION_DESCRIPTION_EVENT_IS_COMING = "Your event is coming";
    public static final String NOTIFICATION_DESCRIPTION_EVENT_WAS_CANCELED = "Event was canceled";

    @Autowired
    private NotificationService notificationService;

    @Test
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        NotificationModel notificationModel = new NotificationModel();

        //When
        NotificationModel createdNotificationModel = notificationService.create(notificationModel);

        //Then
        assertAll(
                () -> assertNotNull(createdNotificationModel, "CreatedNotificationModel is null"),
                () -> assertNotNull(createdNotificationModel.getId(), "CreatedNotificationModel.id is null")
        );
    }

    @Test
    void givenModelAndService_whenCreate_thenServiceListSizeOne() {
        //Given
        NotificationModel notificationModel = new NotificationModel();

        //When
        notificationService.create(notificationModel);
        List<NotificationModel> notificationModels = notificationService.list();

        //Then
        assertAll(
                () -> assertNotNull(notificationModels, "NotificationModels is null"),
                () -> assertEquals(NOTIFICATION_MODELS_SIZE_1, notificationModels.size(), "NotificationModels isn't empty")
        );
    }

    @Test
    void given_whenNotificationNotFound_thenThrowsException() {
        //Given

        //When
        assertThrows(
                NotificationNotFoundException.class, () -> {
                    NotificationModel notificationModel = notificationService.read(NOTIFICATION_ID_NEGATIVE_1);
                }
        );

        //Then

    }

    @Test
    void givenModelAndService_whenUpdate_thenDescriptionUpdated() {
        //Given
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setDescription(NOTIFICATION_DESCRIPTION_EVENT_IS_COMING);

        //When
        notificationService.create(notificationModel);
        notificationModel.setDescription(NOTIFICATION_DESCRIPTION_EVENT_WAS_CANCELED);
        NotificationModel updatedNotificationModel = notificationService.update(notificationModel);

        //Then
        assertAll(
                () -> assertNotNull(updatedNotificationModel, "UpdatedNotificationModel is null"),
                () -> assertEquals(updatedNotificationModel.getDescription(), NOTIFICATION_DESCRIPTION_EVENT_WAS_CANCELED,
                        "UpdatedNotificationModel has different description.")
        );
    }

    @Test
    void given_whenDelete_thenNotificationDeleted() {
        //Given
        NotificationModel notificationModel = new NotificationModel();
        NotificationModel createdNotificationModel = notificationService.create(notificationModel);

        //When
        notificationService.delete(createdNotificationModel);
        List<NotificationModel> notificationModels = notificationService.list();

        //Then
        assertEquals(0, notificationModels.size(), "NotificationModels isn't empty.");
    }
}