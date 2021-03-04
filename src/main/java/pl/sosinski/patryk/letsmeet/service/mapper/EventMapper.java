package pl.sosinski.patryk.letsmeet.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;

import java.util.logging.Logger;

@Component
public class EventMapper {

    private static final Logger LOGGER = Logger.getLogger(EventMapper.class.getName());

    public EventEntity from(EventModel eventModel) {
        LOGGER.info("from(" + eventModel + ")");

        ModelMapper modelMapper = new ModelMapper();
        EventEntity eventEntity = modelMapper.map(eventModel, EventEntity.class);

        LOGGER.info("from(...) = " + eventEntity);
        return eventEntity;
    }

    public EventModel from(EventEntity eventEntity){
        LOGGER.info("from(" + eventEntity + ")");

        ModelMapper modelMapper = new ModelMapper();
        EventModel eventModel = modelMapper.map(eventEntity, EventModel.class);

        LOGGER.info("from(...) = " + eventModel);

        return eventModel;
    }
}
