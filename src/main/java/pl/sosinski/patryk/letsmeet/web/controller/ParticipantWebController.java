package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sosinski.patryk.letsmeet.core.exception.EmailAlreadyExistsException;
import pl.sosinski.patryk.letsmeet.service.ParticipantManagerService;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.ParticipantRequestModel;

import javax.validation.Valid;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.LOGIN_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.PARTICIPANTS_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.REGISTRATION_URL;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.REGISTRATION_VIEW;

@Controller
@RequestMapping(PARTICIPANTS_URL)
public class ParticipantWebController {

    private final Logger LOGGER = Logger.getLogger(ParticipantWebController.class.getName());

    private final ParticipantManagerService participantManagerService;

    public ParticipantWebController(ParticipantManagerService participantManagerService) {
        this.participantManagerService = participantManagerService;
    }

    @GetMapping(REGISTRATION_URL)
    public String showRegistrationForm(ModelMap modelMap) {
        LOGGER.info("showRegistrationForm()");

        modelMap.addAttribute("participant", new ParticipantRequestModel());

        LOGGER.info("showRegistrationForm()");
        return REGISTRATION_VIEW;
    }

    @PostMapping(REGISTRATION_URL)
    public String registerNewParticipant(@ModelAttribute("participant") @Valid ParticipantRequestModel participantRequestModel,
                                         BindingResult bindingResult) throws EmailAlreadyExistsException {
        LOGGER.info("registerNewParticipant(" + participantRequestModel + ")");

        if(bindingResult.hasErrors()){
            return REGISTRATION_VIEW;
        }

        ParticipantModel createdParticipantModel = participantManagerService.create(participantRequestModel);

        LOGGER.info("registerNewParticipant(...) = " + createdParticipantModel);
        return "redirect:" + LOGIN_URL;
    }
}
