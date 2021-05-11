package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class EventCategoryServiceTest {

    public static final String INTEREST_NAME_SPORT = "Sport";
    private static final int INTEREST_MODELS_SIZE_1 = 1;

    @Autowired
    private EventCategoryService eventCategoryService;

    @Test
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        EventCategoryModel eventCategoryModel = EventCategoryModel.builder()
                .name(INTEREST_NAME_SPORT)
                .build();

        //When
        EventCategoryModel createdEventCategoryModel = eventCategoryService.create(eventCategoryModel);

        //Then
        assertAll(
                () -> assertNotNull(createdEventCategoryModel, "CreatedInterest is null"),
                () -> assertNotNull(createdEventCategoryModel.getId(), "CreatedInterest.id is null")
        );
    }

    @Test
    void givenModelAndService_whenCreate_thenServiceListSizeOne() {
        //Given
        EventCategoryModel eventCategoryModel = EventCategoryModel.builder()
                .name(INTEREST_NAME_SPORT)
                .build();

        //When
        eventCategoryService.create(eventCategoryModel);
        List<EventCategoryModel> eventCategoryModels = eventCategoryService.list();

        //Then
        assertAll(
                () -> assertNotNull(eventCategoryModels, "EventModels is null"),
                () -> assertEquals(INTEREST_MODELS_SIZE_1, eventCategoryModels.size(), "EventModels isn't empty")
        );
    }
}