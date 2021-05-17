package pl.sosinski.patryk.letsmeet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.EventRequestModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventManagerServiceTest {

    public static final String PARTICIPANT_FIRST_NAME_PATRYK = "Patryk";
    public static final String EVENT_CATEGORY_NAME_SPORT = "Sport";
    public static final String EVENT_NAME_TRENING = "Trening";
    public static final String EVENT_URL_TRAINING_PL = "www.training.pl";
    @Autowired
    private EventManagerService eventManagerService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private EventCategoryService eventCategoryService;

    @Test
    void givenEventManagerServiceANDEventRequestModel_whenCreate_thenCreatedEventModelNotNull() throws Exception {
        //Given

        ParticipantModel participantModel = ParticipantModel.builder()
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();
        ParticipantModel createdParticipantModel = participantService.create(participantModel);

        EventCategoryModel eventCategoryModel = new EventCategoryModel();
        eventCategoryModel.setName(EVENT_CATEGORY_NAME_SPORT);
        EventCategoryModel createdEventCategoryModel = eventCategoryService.create(eventCategoryModel);

        EventRequestModel eventRequestModel = new EventRequestModel();
        eventRequestModel.setCategoryId(String.valueOf(createdEventCategoryModel.getId()));
        eventRequestModel.setHostId(String.valueOf(createdParticipantModel.getId()));

        //When
        EventModel eventModel = eventManagerService.create(eventRequestModel);

        //Then
        assertNotNull(eventModel);
    }

    @Test
    void givenEventManagerServiceAndEventRequestModel_whenCreate_thenCreatedEventModelEquals() throws Exception {
        //Given

        ParticipantModel participantModel = ParticipantModel.builder()
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();
        ParticipantModel createdParticipantModel = participantService.create(participantModel);

        EventCategoryModel eventCategoryModel = new EventCategoryModel();
        eventCategoryModel.setName(EVENT_CATEGORY_NAME_SPORT);
        EventCategoryModel createdEventCategoryModel = eventCategoryService.create(eventCategoryModel);

        EventRequestModel eventRequestModel = new EventRequestModel();
        eventRequestModel.setHostId(String.valueOf(createdParticipantModel.getId()));
        eventRequestModel.setName(EVENT_NAME_TRENING);
        eventRequestModel.setUrl(EVENT_URL_TRAINING_PL);
        eventRequestModel.setCategoryId(String.valueOf(createdEventCategoryModel.getId()));

        //When
        EventModel eventModel = eventManagerService.create(eventRequestModel);

        //Then
        assertAll(
                () -> assertEquals(Long.valueOf(eventRequestModel.getHostId()), eventModel.getHost().getId()),
                () -> assertEquals(eventRequestModel.getName(), eventModel.getName()),
                () -> assertEquals(eventRequestModel.getUrl(), eventModel.getUrl())
        );
    }

    @Test
    void givenEventManagerServiceAndEventRequestModel_whenCreate_thenEventModelContainsEventCategory() throws Exception {
        //Given

        ParticipantModel participantModel = ParticipantModel.builder()
                .firstName(PARTICIPANT_FIRST_NAME_PATRYK)
                .build();
        ParticipantModel createdParticipantModel = participantService.create(participantModel);

        EventCategoryModel eventCategoryModel = new EventCategoryModel();
        eventCategoryModel.setName(EVENT_CATEGORY_NAME_SPORT);
        EventCategoryModel createdEventCategoryModel = eventCategoryService.create(eventCategoryModel);

        EventRequestModel eventRequestModel = new EventRequestModel();
        eventRequestModel.setHostId(String.valueOf(createdParticipantModel.getId()));
        eventRequestModel.setName(EVENT_NAME_TRENING);
        eventRequestModel.setUrl(EVENT_URL_TRAINING_PL);
        eventRequestModel.setCategoryId(String.valueOf(createdEventCategoryModel.getId()));

        //When
        EventModel eventModel = eventManagerService.create(eventRequestModel);

        //Then
        assertThat(eventModel.getCategories()).contains(createdEventCategoryModel);
    }
}