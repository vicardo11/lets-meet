package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.core.exception.ParticipantNotFoundException;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.EventRequestModel;

import java.util.logging.Logger;

@Service
public class EventManagerService {

    private static final Logger LOGGER = Logger.getLogger(EventManagerService.class.getName());

    private final EventService eventService;
    private final ParticipantService participantService;
    private final EventCategoryService eventCategoryService;

    public EventManagerService(EventService eventService, ParticipantService participantService, EventCategoryService eventCategoryService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.eventCategoryService = eventCategoryService;
    }

    public EventModel create(EventRequestModel eventRequestModel) throws ParticipantNotFoundException, EventCategoryNotFoundException {
        LOGGER.info("create(" + eventRequestModel + ")");

        String hostId = eventRequestModel.getHostId();
        String categoryId = eventRequestModel.getCategoryId();

        ParticipantModel host = participantService.read(Long.valueOf(hostId));
        EventCategoryModel category = eventCategoryService.read(Long.valueOf(categoryId));

        EventModel eventModel = new EventModel();
        eventModel.setHost(host);
        eventModel.addEventCategory(category);
        eventModel.setName(eventRequestModel.getName());
        eventModel.setDateTime(eventRequestModel.getDateTime());
        eventModel.setDurationInMinutes(eventRequestModel.getDurationInMinutes());
        eventModel.setUrl(eventRequestModel.getUrl());

        EventModel createdEventModel = eventService.create(eventModel);

        category.addEvent(createdEventModel);
        eventCategoryService.update(category);

        LOGGER.info("create(...) = " + createdEventModel);
        return createdEventModel;
    }
}
