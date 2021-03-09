package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NotificationRepositoryIntegrationTest {

    public static final String NOTIFICATION_ENTITY_DESCRIPTION_YOUR = "Your event is coming.";
    public static final int NOTIFICATION_ENTITIES_SIZE_1 = 1;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void givenEntityAndRepository_whenSave_thenEntitySaved() {
        //Given
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setDescription(NOTIFICATION_ENTITY_DESCRIPTION_YOUR);

        //When
        NotificationEntity savedNotificationEntity = notificationRepository.save(notificationEntity);
        List<NotificationEntity> notificationEntities = notificationRepository.findAll();
        int notificationEntitiesSize = notificationEntities.size();

        //Then
        assertAll(
                () -> assertNotNull(savedNotificationEntity, "NotificationEntity is null"),
                () -> assertNotNull(savedNotificationEntity.getId(), "NotificationEntity.id is null"),
                () -> assertEquals(NOTIFICATION_ENTITIES_SIZE_1, notificationEntitiesSize, "NotificationEntities size is greater than " + NOTIFICATION_ENTITIES_SIZE_1)
        );
    }
}