package pl.sosinski.patryk.letsmeet.web.controller;

import org.springframework.web.bind.annotation.*;
import pl.sosinski.patryk.letsmeet.service.NotificationService;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    private static final Logger LOGGER = Logger.getLogger(NotificationController.class.getName());

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationModel> list() {

        List<NotificationModel> notificationModels = notificationService.list();

        return notificationModels;
    }

    @PostMapping
    public NotificationModel create(@RequestBody NotificationModel notificationModel) {
        LOGGER.info("create(" + notificationModel + ")");
        NotificationModel createdNotificationModel = notificationService.create(notificationModel);

        LOGGER.info("create(...) = " + createdNotificationModel);
        return createdNotificationModel;
    }
}
