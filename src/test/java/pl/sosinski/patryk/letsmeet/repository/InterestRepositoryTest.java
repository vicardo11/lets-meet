package pl.sosinski.patryk.letsmeet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class InterestRepositoryTest {

    @Autowired
    private InterestRepository interestRepository;

    @Test
    void givenInterestEntityAndRepository_whenSave_thenEntityNotNull() {
        //Given
        InterestEntity interestEntity = new InterestEntity();

        //When
        InterestEntity savedInterestEntity = interestRepository.save(interestEntity);

        //Then
        assertAll(
                () -> assertNotNull(savedInterestEntity, "InterestEntity is null"),
                () -> assertNotNull(savedInterestEntity.getId(), "InterestEntity.id is null")
        );
    }

}