package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sosinski.patryk.letsmeet.service.EventService;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;

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

    public EventWebController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String list(ModelMap modelMap) {
        LOGGER.info("list()");
        List<EventModel> events = eventService.list();
        events.add(EventModel.builder()
                .id(1L)
                .name("Java szkolenie")
                .build());

        modelMap.addAttribute(EVENTS_ATTRIBUTE, events);

        LOGGER.info("list() = " + events);
        return EVENTS_VIEW;
    }
}
