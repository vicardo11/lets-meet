package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.EVENTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.LOGIN_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.LOGIN_VIEW;

@Controller
public class MainController {

    @GetMapping
    public String redirect() {
        return "redirect:" + EVENTS_URL;
    }

    @GetMapping(LOGIN_URL)
    public String showLoginPage() {
        return LOGIN_VIEW;
    }
}
