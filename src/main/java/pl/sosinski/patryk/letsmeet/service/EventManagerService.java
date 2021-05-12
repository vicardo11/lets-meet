package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.core.exception.ParticipantNotFoundException;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.EventRequestModel;

@Service
public class EventManagerService {

    private final EventService eventService;
    private final ParticipantService participantService;
    private final EventCategoryService eventCategoryService;

    public EventManagerService(EventService eventService, ParticipantService participantService, EventCategoryService eventCategoryService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.eventCategoryService = eventCategoryService;
    }

    public EventModel create(EventRequestModel eventRequestModel) throws ParticipantNotFoundException, EventCategoryNotFoundException {
        String hostId = eventRequestModel.getHostId();
        String categoryId = eventRequestModel.getCategoryId();

        ParticipantModel host = participantService.read(Long.valueOf(hostId));
        EventCategoryModel category = eventCategoryService.read(Long.valueOf(categoryId));

        EventModel eventModel = new EventModel();
        eventModel.setHost(host);
        eventModel.getCategories().add(category);
        eventModel.setName(eventRequestModel.getName());
        eventModel.setDateTime(eventRequestModel.getDateTime());
        eventModel.setDurationInMinutes(eventRequestModel.getDurationInMinutes());
        eventModel.setUrl(eventRequestModel.getUrl());

        EventModel createdEventModel = eventService.create(eventModel);

        return createdEventModel;
    }
}
