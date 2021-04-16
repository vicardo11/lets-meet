package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sosinski.patryk.letsmeet.core.exception.InterestNotFoundException;
import pl.sosinski.patryk.letsmeet.service.InterestService;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.INTEREST_URI;

@RestController
@RequestMapping(value = INTEREST_URI)
public class InterestController {

    private static final Logger LOGGER = Logger.getLogger(InterestController.class.getName());

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public List<InterestModel> list() {
        LOGGER.info("list()");
        List<InterestModel> interestModels = interestService.list();

        LOGGER.info("list() = " + interestModels);
        return interestModels;
    }

    @GetMapping(value = "/{id}")
    public InterestModel read(@PathVariable(name = "id") Long id) throws InterestNotFoundException {
        LOGGER.info("read(" + id + ")");
        InterestModel interestModel = interestService.read(id);

        LOGGER.info("read(...) = " + interestModel);
        return interestModel;
    }

    @PostMapping
    public InterestModel create(@RequestBody InterestModel interestModel) {
        LOGGER.info("create(" + interestModel + ")");
        InterestModel createdInterestModel = interestService.create(interestModel);

        LOGGER.info("create(...) = " + createdInterestModel);
        return createdInterestModel;
    }

    @PutMapping(value = "/{id}")
    public InterestModel update(@RequestBody InterestModel interestModel) {
        LOGGER.info("update(" + interestModel + ")");
        InterestModel updatedInterestModel = interestService.update(interestModel);

        LOGGER.info("update(...) = " + updatedInterestModel);
        return updatedInterestModel;
    }
}
