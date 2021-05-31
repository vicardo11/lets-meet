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
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.ADD_EVENT_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENTS_VIEW;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENT_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENT_CATEGORIES_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.PARTICIPANT_EVENTS_VIEW;

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

        loadCategoriesForAddingNewEvent(modelMap);
        modelMap.addAttribute(EVENT_ATTRIBUTE, new EventRequestModel());

        return ADD_EVENT_VIEW;
    }

    @PostMapping
    public String create(@Valid @ModelAttribute(name = "event") EventRequestModel eventRequestModel,
                         BindingResult bindingResult, ModelMap modelMap, Principal principal)
            throws EventCategoryNotFoundException, ParticipantNotFoundException {
        LOGGER.info("create(" + eventRequestModel + ")");

        if (bindingResult.hasErrors()) {
            loadCategoriesForAddingNewEvent(modelMap);
            return ADD_EVENT_VIEW;
        }

        ParticipantModel participantModel = getLoggedParticipantModel(principal);
        eventRequestModel.setHostId(String.valueOf(participantModel.getId()));

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
    public String listByEventName(@RequestParam("eventName") String eventName, ModelMap modelMap) {
        LOGGER.info("listByEventName()");

        List<EventModel> events = eventService.listByEventName(eventName);
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);
        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("listByEventName() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping("/my-events")
    public String eventsOfLoggedUser(Principal principal, ModelMap modelMap) {
        LOGGER.info("eventsOfLoggedUser()");

        ParticipantModel participantModel = getLoggedParticipantModel(principal);

        List<EventModel> events = eventService.listByParticipant(participantModel);

        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("eventsOfLoggedUser() = " + events);
        return PARTICIPANT_EVENTS_VIEW;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("eventId") Long eventId) {
        LOGGER.info("delete(" + eventId + ")");

        eventService.delete(eventId);

        LOGGER.info("delete(...)");
        return "redirect:" + EVENTS_URL;
    }

    private ParticipantModel getLoggedParticipantModel(Principal principal) {
        String email = principal.getName();
        return participantService.findByEmail(email);
    }

    private void loadCategoriesForAddingNewEvent(ModelMap modelMap) {
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute(EVENT_CATEGORIES_ATTRIBUTE, categories);
    }

}
