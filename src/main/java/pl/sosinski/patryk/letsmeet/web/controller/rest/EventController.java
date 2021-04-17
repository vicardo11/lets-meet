package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sosinski.patryk.letsmeet.core.exception.EventNotFoundException;
import pl.sosinski.patryk.letsmeet.core.exception.InterestNotFoundException;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.service.InterestService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URI;

@RestController
@RequestMapping(value = EVENTS_URI)
public class EventController {

    private static final Logger LOGGER = Logger.getLogger(EventController.class.getName());

    private final EventService eventService;
    private final InterestService interestService;

    public EventController(EventService eventService, InterestService interestService) {
        this.eventService = eventService;
        this.interestService = interestService;
    }

    @GetMapping
    public List<EventModel> list() {
        LOGGER.info("list()");
        List<EventModel> eventModels = eventService.list();

        LOGGER.info("list() = " + eventModels);
        return eventModels;
    }

    @GetMapping(value = "/by-interest/{interestId}")
    public List<EventModel> listByInterest(@PathVariable Long interestId) throws InterestNotFoundException {
        LOGGER.info("listByInterest(" + interestId + ")");
        InterestModel interestModel = interestService.read(interestId);

        List<EventModel> eventModelsByInterest = eventService.listByInterest(interestModel);
        LOGGER.info("listByInterest(...) = " + eventModelsByInterest);
        return eventModelsByInterest;
    }

    @GetMapping(value = "/{id}")
    public EventModel read(@PathVariable(name = "id") Long id) throws EventNotFoundException {
        LOGGER.info("read(" + id + ")");
        EventModel eventModel = eventService.read(id);

        LOGGER.info("read(...) = " + eventModel);
        return eventModel;
    }

    @PostMapping
    public EventModel create(@RequestBody EventModel eventModel) {
        LOGGER.info("create(" + eventModel + ")");
        EventModel createdEventModel = eventService.create(eventModel);

        LOGGER.info("create(...) = " + createdEventModel);
        return createdEventModel;
    }

    @PutMapping(value = "/{id}")
    public EventModel update(@RequestBody EventModel eventModel) {
        LOGGER.info("update(" + eventModel + ")");
        EventModel updatedEventModel = eventService.update(eventModel);

        LOGGER.info("update(...) = " + updatedEventModel);
        return updatedEventModel;
    }
}
