package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.EVENTS_URL;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String redirect() {
        return "redirect:" + EVENTS_URL;
    }
}
