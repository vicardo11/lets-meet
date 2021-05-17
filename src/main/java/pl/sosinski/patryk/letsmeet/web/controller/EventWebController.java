package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.core.exception.ParticipantNotFoundException;
import pl.sosinski.patryk.letsmeet.service.EventCategoryService;
import pl.sosinski.patryk.letsmeet.service.EventManagerService;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.service.ParticipantService;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.EventRequestModel;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.ADD_EVENT_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENT_CATEGORIES_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.PARTICIPANTS_ATTRIBUTE;

@Controller
@RequestMapping(value = EVENTS_URL)
public class EventWebController {

    private static final Logger LOGGER = Logger.getLogger(EventWebController.class.getName());

    private final EventService eventService;
    private final ParticipantService participantService;
    private final EventCategoryService eventCategoryService;
    private final EventManagerService eventManagerService;

    public EventWebController(EventService eventService, ParticipantService participantService, EventCategoryService eventCategoryService, EventManagerService eventManagerService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.eventCategoryService = eventCategoryService;
        this.eventManagerService = eventManagerService;
    }

    @GetMapping
    public String list(ModelMap modelMap) {
        LOGGER.info("list()");

        List<EventModel> events = eventService.list();
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);
        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);

        LOGGER.info("list() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping(value = "/add")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");

        loadAttributesForAddingNewEvent(modelMap);
        modelMap.addAttribute(EVENT_ATTRIBUTE, new EventRequestModel());

        return ADD_EVENT_VIEW;
    }

    @PostMapping
    public String create(@Valid @ModelAttribute(name = "event") EventRequestModel eventRequestModel, BindingResult bindingResult, ModelMap modelMap)
            throws EventCategoryNotFoundException, ParticipantNotFoundException {
        LOGGER.info("create(" + eventRequestModel + ")");

        if (bindingResult.hasErrors()){
            loadAttributesForAddingNewEvent(modelMap);
            return ADD_EVENT_VIEW;
        }

        EventModel eventModel = eventManagerService.create(eventRequestModel);

        LOGGER.info("create(...) = " + eventModel);
        return "redirect:" + EVENTS_URL;
    }

    @GetMapping("/by-category")
    public String listByEventCategory(@RequestParam("eventCategoryId") Long eventCategoryId, ModelMap modelMap) throws EventCategoryNotFoundException {
        LOGGER.info("listByEventCategory()");

        EventCategoryModel eventCategoryModel = eventCategoryService.read(eventCategoryId);
        List<EventModel> events = eventService.listByEventCategory(eventCategoryModel);

        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);
        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("listByEventCategory() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping("/by-name")
    public String listByEventName(@RequestParam("eventName") String eventName, ModelMap modelMap) throws EventCategoryNotFoundException {
        LOGGER.info("listByEventName()");

        List<EventModel> events = eventService.listByEventName(eventName);
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);
        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("listByEventName() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("eventId") Long eventId) {
        LOGGER.info("delete(" + eventId + ")");

        eventService.delete(eventId);

        LOGGER.info("delete(...)");
        return "redirect:" + EVENTS_URL;
    }

    private void loadAttributesForAddingNewEvent(ModelMap modelMap) {
        List<ParticipantModel> participants = participantService.list();
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(PARTICIPANTS_ATTRIBUTE, participants);
        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);
    }

}
