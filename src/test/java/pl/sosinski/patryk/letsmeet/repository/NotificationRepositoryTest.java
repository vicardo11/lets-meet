package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationRepositoryTest {

    public static final String NOTIFICATION_ENTITY_DESCRIPTION_YOUR = "Your event is coming.";

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void givenEntityAndRepository_whenSave_thenEntitySaved() {
        //Given
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setDescription(NOTIFICATION_ENTITY_DESCRIPTION_YOUR);

        //When
        NotificationEntity savedNotificationEntity = notificationRepository.save(notificationEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedNotificationEntity, "NotificationEntity is null"),
                () -> assertNotNull(savedNotificationEntity.getId(), "NotificationEntity.id is null")
        );
    }
}