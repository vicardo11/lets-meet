package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;
import pl.sosinski.patryk.letsmeet.core.exception.NotificationNotFoundException;
import pl.sosinski.patryk.letsmeet.repository.NotificationRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.NotificationMapper;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationModel> list() {
        LOGGER.info("list()");
        List<NotificationEntity> notificationEntities = notificationRepository.findAll();

        List<NotificationModel> notificationModels = notificationMapper.fromEntities(notificationEntities);

        LOGGER.info("list(...)");
        return notificationModels;
    }

    public NotificationModel create(NotificationModel notificationModel) {
        LOGGER.info("create(" + notificationModel + ")");

        NotificationEntity notificationEntity = notificationMapper.from(notificationModel);
        NotificationEntity savedNotificationEntity = notificationRepository.save(notificationEntity);
        NotificationModel savedNotificationModel = notificationMapper.from(savedNotificationEntity);

        LOGGER.info("create(...) = " + savedNotificationModel);
        return savedNotificationModel;
    }

    public NotificationModel read(Long notificationId) throws LetsMeetException {
        LOGGER.info("read(" + notificationId + ")");

        Optional<NotificationEntity> optionalNotificationEntity = notificationRepository.findById(notificationId);
        NotificationEntity notificationEntity = optionalNotificationEntity.orElseThrow(
                () -> new NotificationNotFoundException("Notification not found for id: " + notificationId));

//      NotificationEntity notificationEntity = notificationRepository.findById(notificationId).get();
        NotificationModel notificationModel = notificationMapper.from(notificationEntity);

        LOGGER.info("read(...) = " + notificationModel);
        return notificationModel;
    }

    public NotificationModel update(NotificationModel notificationModel) {
        LOGGER.info("update(" + notificationModel + ")");

        NotificationEntity notificationEntity = notificationMapper.from(notificationModel);
        NotificationEntity updatedNotificationEntity = notificationRepository.save(notificationEntity);
        NotificationModel updatedNotificationModel = notificationMapper.from(updatedNotificationEntity);

        LOGGER.info("update(...) = " + updatedNotificationModel);
        return updatedNotificationModel;
    }

    public void delete(NotificationModel notificationModel) {
        LOGGER.info("delete(" + notificationModel + ")");

        NotificationEntity notificationEntity = notificationMapper.from(notificationModel);
        notificationRepository.delete(notificationEntity);

        LOGGER.info("delete(...) = " + notificationModel);
    }


}
