package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.core.exception.ParticipantNotFoundException;
import pl.sosinski.patryk.letsmeet.service.EventManagerService;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.service.EventCategoryService;
import pl.sosinski.patryk.letsmeet.service.ParticipantService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.EventRequestModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_ATTRIBUTE;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_VIEW;

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

        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("list() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping(value = "/add")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");

        List<ParticipantModel> participants = participantService.list();
        List<EventCategoryModel> categories = eventCategoryService.list();

        modelMap.addAttribute("event", new EventModel());
        modelMap.addAttribute("participants", participants);
        modelMap.addAttribute("categories", categories);

        return "/events/add-event";
    }

    @PostMapping
    public String create(@ModelAttribute(name = "event")EventRequestModel eventRequestModel, HttpServletRequest request)
            throws EventCategoryNotFoundException, ParticipantNotFoundException {
        LOGGER.info("create()");

        EventModel eventModel = eventManagerService.create(eventRequestModel);

        return "redirect:" + EVENTS_URL;
    }
}
