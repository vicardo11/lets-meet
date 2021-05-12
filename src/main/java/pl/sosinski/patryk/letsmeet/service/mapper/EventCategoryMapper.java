package pl.sosinski.patryk.letsmeet.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class EventCategoryMapper {

    private static final Logger LOGGER = Logger.getLogger(EventCategoryMapper.class.getName());

    public EventCategoryEntity from(EventCategoryModel eventCategoryModel) {
        LOGGER.info("from(" + eventCategoryModel + ")");

        ModelMapper modelMapper = new ModelMapper();
        EventCategoryEntity eventCategoryEntity = modelMapper.map(eventCategoryModel, EventCategoryEntity.class);

        LOGGER.info("from(...) = " + eventCategoryEntity);
        return eventCategoryEntity;
    }

    public EventCategoryModel from(EventCategoryEntity eventCategoryEntity) {
        LOGGER.info("from(" + eventCategoryEntity + ")");

        ModelMapper modelMapper = new ModelMapper();
        EventCategoryModel eventCategoryModel = modelMapper.map(eventCategoryEntity, EventCategoryModel.class);

        LOGGER.info("from(...) = " + eventCategoryModel);

        return eventCategoryModel;
    }

    public List<EventCategoryModel> fromEntities(List<EventCategoryEntity> interestEntities) {
        return interestEntities.stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
