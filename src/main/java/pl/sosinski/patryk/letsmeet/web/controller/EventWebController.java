package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private List<EventModel> events = new ArrayList<>();

    public EventWebController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String list(ModelMap modelMap) {
        LOGGER.info("list()");

        events = eventService.list();

        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("list() = " + events);
        return EVENTS_VIEW;
    }

    @GetMapping(value = "/add")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");

        modelMap.addAttribute("event", new EventModel());

        return "/events/add-event";
    }

    @PostMapping
    public String create(@ModelAttribute(name = "event") EventModel eventModel, HttpServletRequest request) {
        LOGGER.info("create()");

        events.add(eventModel);

        return "redirect:" + EVENTS_URL;
    }
}
