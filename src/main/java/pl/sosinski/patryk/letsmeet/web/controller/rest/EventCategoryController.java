package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.service.EventCategoryService;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_CATEGORY_URI;

@RestController
@RequestMapping(value = EVENT_CATEGORY_URI)
public class EventCategoryController {

    private static final Logger LOGGER = Logger.getLogger(EventCategoryController.class.getName());

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @GetMapping
    public List<EventCategoryModel> list() {
        LOGGER.info("list()");
        List<EventCategoryModel> eventCategoryModels = eventCategoryService.list();

        LOGGER.info("list() = " + eventCategoryModels);
        return eventCategoryModels;
    }

    @GetMapping(value = "/{id}")
    public EventCategoryModel read(@PathVariable(name = "id") Long id) throws EventCategoryNotFoundException {
        LOGGER.info("read(" + id + ")");
        EventCategoryModel eventCategoryModel = eventCategoryService.read(id);

        LOGGER.info("read(...) = " + eventCategoryModel);
        return eventCategoryModel;
    }

    @PostMapping
    public EventCategoryModel create(@RequestBody EventCategoryModel eventCategoryModel) {
        LOGGER.info("create(" + eventCategoryModel + ")");
        EventCategoryModel createdEventCategoryModel = eventCategoryService.create(eventCategoryModel);

        LOGGER.info("create(...) = " + createdEventCategoryModel);
        return createdEventCategoryModel;
    }

    @PutMapping(value = "/{id}")
    public EventCategoryModel update(@RequestBody EventCategoryModel eventCategoryModel) {
        LOGGER.info("update(" + eventCategoryModel + ")");
        EventCategoryModel updatedEventCategoryModel = eventCategoryService.update(eventCategoryModel);

        LOGGER.info("update(...) = " + updatedEventCategoryModel);
        return updatedEventCategoryModel;
    }
}
