package pl.sosinski.patryk.letsmeet.web.controller.rest;

import org.springframework.web.bind.annotation.*;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;
import pl.sosinski.patryk.letsmeet.service.NotificationService;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.List;
import java.util.logging.Logger;

import static pl.sosinski.patryk.letsmeet.web.controller.rest.ControllerConstants.NOTIFICATIONS_URI;

@RestController
@RequestMapping(value = NOTIFICATIONS_URI)
public class NotificationController {

    private static final Logger LOGGER = Logger.getLogger(NotificationController.class.getName());

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationModel> list() {
        LOGGER.info("list()");
        List<NotificationModel> notificationModels = notificationService.list();

        LOGGER.info("list() = " + notificationModels);
        return notificationModels;
    }

    @PostMapping
    public NotificationModel create(@RequestBody NotificationModel notificationModel) {
        LOGGER.info("create(" + notificationModel + ")");
        NotificationModel createdNotificationModel = notificationService.create(notificationModel);

        LOGGER.info("create(...) = " + createdNotificationModel);
        return createdNotificationModel;
    }

    @GetMapping(value = "/{id}")
    public NotificationModel read(@PathVariable(name = "id") Long id) throws LetsMeetException {
        LOGGER.info("read(" + id + ")");
        NotificationModel notificationModel = notificationService.read(id);

        LOGGER.info("read(...) = " + notificationModel);
        return notificationModel;
    }

    @PutMapping(value = "/{id}")
    public NotificationModel update(@PathVariable(name = "id") Long id, @RequestBody NotificationModel notificationModel) throws LetsMeetException {
        LOGGER.info("update(" + id + ")");
        NotificationModel updateNotificationModel = notificationService.update(notificationModel);

        LOGGER.info("update(...) = " + updateNotificationModel);
        return updateNotificationModel;
    }

}
