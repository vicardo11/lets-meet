package pl.sosinski.patryk.letsmeet.service.mapper;

import org.junit.jupiter.api.Test;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import static org.junit.jupiter.api.Assertions.*;

class InterestMapperTest {

    public static final long INTEREST_ID_1 = 1L;
    public static final String INTEREST_NAME_SPORT = "Sport";

    @Test
    void givenMapper_whenFrom_thenEntityEquals() {
        //Given
        InterestMapper interestMapper = new InterestMapper();
        InterestModel interestModel = new InterestModel();
        interestModel.setId(INTEREST_ID_1);
        interestModel.setName(INTEREST_NAME_SPORT);

        //When
        InterestEntity interestEntity = interestMapper.from(interestModel);

        //Then
        assertAll(
                () -> assertNotNull(interestEntity, "InterestEntity is null"),
                () -> assertNotNull(interestEntity.getId(), "InterestEntity.id is null"),
                () -> assertNotNull(interestEntity.getName(), "InterestEntity.name is null")
        );

    }

    @Test
    void givenMapper_whenFrom_thenModelEquals() {
        //Given
        InterestMapper interestMapper = new InterestMapper();
        InterestEntity interestEntity = new InterestEntity();
        interestEntity.setId(INTEREST_ID_1);
        interestEntity.setName(INTEREST_NAME_SPORT);

        //When
        InterestModel interestModel = interestMapper.from(interestEntity);

        //Then
        assertAll(
                () -> assertNotNull(interestModel, "InterestModel is null"),
                () -> assertNotNull(interestModel.getId(), "InterestModel.id is null"),
                () -> assertNotNull(interestModel.getName(), "InterestModel.name is null")
        );
    }
}