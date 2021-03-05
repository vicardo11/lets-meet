package pl.sosinski.patryk.letsmeet.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;
import pl.sosinski.patryk.letsmeet.web.model.NotificationModel;

import java.util.logging.Logger;

@Component
public class NotificationMapper {

    private static final Logger LOGGER = Logger.getLogger(NotificationMapper.class.getName());

    public NotificationModel from(NotificationEntity notificationEntity) {
        LOGGER.info("from(" + notificationEntity + ")");

        ModelMapper modelMapper = new ModelMapper();
        NotificationModel notificationModel = modelMapper.map(notificationEntity, NotificationModel.class);

        LOGGER.info("from(...) = " + notificationModel);

        return notificationModel;
    }

    public NotificationEntity from(NotificationModel notificationModel) {
        LOGGER.info("from(" + notificationModel + ")");

        ModelMapper modelMapper = new ModelMapper();
        NotificationEntity notificationEntity = modelMapper.map(notificationModel, NotificationEntity.class);

        LOGGER.info("from(...) = " + notificationEntity);

        return notificationEntity;
    }
}
