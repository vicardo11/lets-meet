package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class EventCategoryRepositoryTest {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Test
    void givenInterestEntityAndRepository_whenSave_thenEntityNotNull() {
        //Given
        EventCategoryEntity eventCategoryEntity = new EventCategoryEntity();

        //When
        EventCategoryEntity savedEventCategoryEntity = eventCategoryRepository.save(eventCategoryEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedEventCategoryEntity, "InterestEntity is null"),
                () -> assertNotNull(savedEventCategoryEntity.getId(), "InterestEntity.id is null")
        );
    }

}