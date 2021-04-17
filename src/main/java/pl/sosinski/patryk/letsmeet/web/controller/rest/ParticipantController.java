package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;
import pl.sosinski.patryk.letsmeet.service.ParticipantService;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.PARTICIPANTS_URI;

@RestController
@RequestMapping(value = PARTICIPANTS_URI)
public class ParticipantController {

    private static final Logger LOGGER = Logger.getLogger(ParticipantController.class.getName());

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping(value = "/{id}")
    public ParticipantModel read(@PathVariable(name = "id") Long id) throws LetsMeetException {
        LOGGER.info("read(" + id + ")");
        ParticipantModel participantModel = participantService.read(id);

        LOGGER.info("read(...) = " + participantModel);
        return participantModel;
    }

    @GetMapping
    public List<ParticipantModel> list() {
        LOGGER.info("list()");
        List<ParticipantModel> participantModels = participantService.list();

        LOGGER.info("list() = " + participantModels);
        return participantModels;
    }

    @PostMapping
    public ParticipantModel create(@RequestBody ParticipantModel participantModel) {
        LOGGER.info("create(" + participantModel + ")");
        ParticipantModel createdParticipantModel = participantService.create(participantModel);

        LOGGER.info("create(...) = " + createdParticipantModel);
        return createdParticipantModel;
    }

    @PutMapping
    public ParticipantModel update(@RequestBody ParticipantModel participantModel) {
        LOGGER.info("update(" + participantModel + ")");
        ParticipantModel updatedParticipantModel = participantService.update(participantModel);

        LOGGER.info("update(...) =" + updatedParticipantModel);
        return updatedParticipantModel;
    }
}
