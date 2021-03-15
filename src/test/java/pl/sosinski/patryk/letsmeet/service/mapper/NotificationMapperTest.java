package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMapperTest {

    public static final long NOTIFICATION_ID_1 = 1L;
    public static final String NOTIFICATION_DESCRIPTION_EVENT_IS_COMING = "Your event is coming.";
    public static final int NOTIFICATION_MODELS_SIZE_2 = 2;
    public static final int NOTIFICATION_ENTITIES_SIZE_0 = 0;
    public static final int NOTIFICATION_MODELS_SIZE_0 = 0;
    private static final int NOTIFICATION_ENTITIES_SIZE_2 = 2;

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

    @Test
    void givenMapper_whenFromEmptyEntitiesList_thenModelsListEmpty() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        List<NotificationEntity> notificationEntities = new ArrayList<>();

        //When
        List<NotificationModel> notificationModels = notificationMapper.fromEntities(notificationEntities);

        //Then
        assertAll(
                () -> assertNotNull(notificationModels, "NotificationModels is null"),
                () -> assertEquals(NOTIFICATION_MODELS_SIZE_0, notificationModels.size(),
                        "NotificationModels isn't empty")
        );
    }

    @Test
    void givenMapper_whenFromEntitiesList_thenModelListHasSameSize() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        NotificationEntity notificationEntity = new NotificationEntity();
        NotificationEntity notificationEntity2 = new NotificationEntity();
        notificationEntities.add(notificationEntity);
        notificationEntities.add(notificationEntity2);

        //When
        List<NotificationModel> notificationModels = notificationMapper.fromEntities(notificationEntities);

        //Then
        assertAll(
                () -> assertNotNull(notificationModels, "NotificationModels is null"),
                () -> assertEquals(NOTIFICATION_MODELS_SIZE_2, notificationModels.size(),
                        "NotificationModels.size isn't equal.")
        );
    }

    @Test
    void givenMapper_whenFromEmptyModelsList_thenEntitiesListEmpty() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        List<NotificationModel> notificationModels = new ArrayList<>();

        //When
        List<NotificationEntity> notificationEntities = notificationMapper.fromModels(notificationModels);

        //Then
        assertAll(
                () -> assertNotNull(notificationEntities, "NotificationEntities is null"),
                () -> assertEquals(NOTIFICATION_ENTITIES_SIZE_0, notificationEntities.size(),
                        "NotificationEntities isn't empty")
        );
    }

    @Test
    void givenMapper_whenFromModelsList_thenEntityListHasSameSize() {
        //Given
        NotificationMapper notificationMapper = new NotificationMapper();
        List<NotificationModel> notificationModels = new ArrayList<>();
        NotificationModel notificationModel = new NotificationModel();
        NotificationModel notificationModel2 = new NotificationModel();
        notificationModels.add(notificationModel);
        notificationModels.add(notificationModel2);

        //When
        List<NotificationEntity> notificationEntities = notificationMapper.fromModels(notificationModels);

        //Then
        assertAll(
                () -> assertNotNull(notificationEntities, "NotificationEntities is null"),
                () -> assertEquals(NOTIFICATION_ENTITIES_SIZE_2, notificationEntities.size(),
                        "NotificationEntities.size isn't equal.")
        );
    }
}