package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import static org.junit.jupiter.api.Assertions.*;

class EventCategoryMapperTest {

    public static final long EVENT_CATEGORY_ID_1 = 1L;
    public static final String EVENT_CATEGORY_NAME_SPORT = "Sport";

    @Test
    void givenMapper_whenFrom_thenEntityEquals() {
        //Given
        EventCategoryMapper eventCategoryMapper = new EventCategoryMapper();
        EventCategoryModel eventCategoryModel = new EventCategoryModel();
        eventCategoryModel.setId(EVENT_CATEGORY_ID_1);
        eventCategoryModel.setName(EVENT_CATEGORY_NAME_SPORT);

        //When
        EventCategoryEntity eventCategoryEntity = eventCategoryMapper.from(eventCategoryModel);

        //Then
        assertAll(
                () -> assertNotNull(eventCategoryEntity, "InterestEntity is null"),
                () -> assertNotNull(eventCategoryEntity.getId(), "InterestEntity.id is null"),
                () -> assertNotNull(eventCategoryEntity.getName(), "InterestEntity.name is null")
        );

    }

    @Test
    void givenMapper_whenFrom_thenModelEquals() {
        //Given
        EventCategoryMapper eventCategoryMapper = new EventCategoryMapper();
        EventCategoryEntity eventCategoryEntity = new EventCategoryEntity();
        eventCategoryEntity.setId(EVENT_CATEGORY_ID_1);
        eventCategoryEntity.setName(EVENT_CATEGORY_NAME_SPORT);

        //When
        EventCategoryModel eventCategoryModel = eventCategoryMapper.from(eventCategoryEntity);

        //Then
        assertAll(
                () -> assertNotNull(eventCategoryModel, "InterestModel is null"),
                () -> assertNotNull(eventCategoryModel.getId(), "InterestModel.id is null"),
                () -> assertNotNull(eventCategoryModel.getName(), "InterestModel.name is null")
        );
    }
}