package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sosinski.patryk.letsmeet.core.exception.EventNotFoundException;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URI;

@RestController
@RequestMapping(value = EVENTS_URI)
public class EventController {

    private static final Logger LOGGER = Logger.getLogger(EventController.class.getName());

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventModel> list() {
        LOGGER.info("list()");
        List<EventModel> eventModels = eventService.list();

        LOGGER.info("list() = " + eventModels);
        return eventModels;
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
