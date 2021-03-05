package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMapperTest {

    public static final long NOTIFICATION_ID_1 = 1L;
    public static final String NOTIFICATION_DESCRIPTION_EVENT_IS_COMING = "Your event is coming.";

    @Test
    void givenMapper_whenFrom_thenModelEquals() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(NOTIFICATION_ID_1);
        notificationEntity.setDescription(NOTIFICATION_DESCRIPTION_EVENT_IS_COMING);

        //When
        NotificationModel notificationModel = notificationMapper.from(notificationEntity);

        //Then
        assertAll(
                () -> assertNotNull(notificationModel, "NotificationModel is null"),
                () -> assertNotNull(notificationModel.getId(), "NotificationModel.id is null"),
                () -> assertNotNull(notificationModel.getDescription(), "NotificationModel.description is null")
        );
    }

    @Test
    void givenMapper_whenFrom_thenEntityEquals() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setId(NOTIFICATION_ID_1);
        notificationModel.setDescription(NOTIFICATION_DESCRIPTION_EVENT_IS_COMING);

        //When
        NotificationEntity notificationEntity = notificationMapper.from(notificationModel);

        //Then
        assertAll(
                () -> assertNotNull(notificationEntity, "NotificationEntity is null"),
                () -> assertNotNull(notificationEntity.getId(), "NotificationEntity.id is null"),
                () -> assertNotNull(notificationEntity.getDescription(), "NotificationEntity.description is null")
        );
    }
}