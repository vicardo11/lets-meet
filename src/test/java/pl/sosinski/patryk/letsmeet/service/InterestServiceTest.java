package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class InterestServiceTest {

    public static final String INTEREST_NAME_SPORT = "Sport";
    private static final int INTEREST_MODELS_SIZE_1 = 1;

    @Autowired
    private InterestService interestService;

    @Test
    void givenModelAndService_whenCreate_thenCreatedModelNotNull() {
        //Given
        InterestModel interestModel = InterestModel.builder()
                .name(INTEREST_NAME_SPORT)
                .build();

        //When
        InterestModel createdInterestModel = interestService.create(interestModel);

        //Then
        assertAll(
                () -> assertNotNull(createdInterestModel, "CreatedInterest is null"),
                () -> assertNotNull(createdInterestModel.getId(), "CreatedInterest.id is null")
        );
    }

    @Test
    void givenModelAndService_whenCreate_thenServiceListSizeOne() {
        //Given
        InterestModel interestModel = InterestModel.builder()
                .name(INTEREST_NAME_SPORT)
                .build();

        //When
        interestService.create(interestModel);
        List<InterestModel> interestModels = interestService.list();

        //Then
        assertAll(
                () -> assertNotNull(interestModels, "EventModels is null"),
                () -> assertEquals(INTEREST_MODELS_SIZE_1, interestModels.size(), "EventModels isn't empty")
        );
    }
}