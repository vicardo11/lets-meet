package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.EventCategoryNotFoundException;
import pl.sosinski.patryk.letsmeet.repository.EventCategoryRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.EventCategoryMapper;
import pl.sosinski.patryk.letsmeet.web.model.EventCategoryModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventCategoryService {

    private static final Logger LOGGER = Logger.getLogger(EventCategoryService.class.getName());

    private final EventCategoryRepository eventCategoryRepository;
    private final EventCategoryMapper eventCategoryMapper;

    public EventCategoryService(EventCategoryRepository eventCategoryRepository, EventCategoryMapper eventCategoryMapper) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventCategoryMapper = eventCategoryMapper;
    }

    public List<EventCategoryModel> list() {
        LOGGER.info("list()");
        List<EventCategoryEntity> interestEntities = eventCategoryRepository.findAll();

        List<EventCategoryModel> eventCategoryModels = eventCategoryMapper.fromEntities(interestEntities);

        LOGGER.info("list(...)");
        return eventCategoryModels;
    }

    public EventCategoryModel create(EventCategoryModel eventCategoryModel) {
        LOGGER.info("create(" + eventCategoryModel + ")");

        EventCategoryEntity eventCategoryEntity = eventCategoryMapper.from(eventCategoryModel);
        EventCategoryEntity savedEventCategoryEntity = eventCategoryRepository.save(eventCategoryEntity);
        EventCategoryModel savedEventCategoryModel = eventCategoryMapper.from(savedEventCategoryEntity);

        LOGGER.info("create(...) = " + savedEventCategoryModel);
        return savedEventCategoryModel;
    }

    public EventCategoryModel read(Long interestId) throws EventCategoryNotFoundException {
        LOGGER.info("read(" + interestId + ")");

        Optional<EventCategoryEntity> optionalInterestEntity = eventCategoryRepository.findById(interestId);
        EventCategoryEntity eventCategoryEntity = optionalInterestEntity.orElseThrow(
                () -> new EventCategoryNotFoundException("Interest not found for id: " + interestId)
        );
        EventCategoryModel eventCategoryModel = eventCategoryMapper.from(eventCategoryEntity);

        LOGGER.info("read(" + interestId + ")");
        return eventCategoryModel;
    }

    public EventCategoryModel update(EventCategoryModel eventCategoryModel) {
        LOGGER.info("update(" + eventCategoryModel + ")");

        EventCategoryEntity eventCategoryEntity = eventCategoryMapper.from(eventCategoryModel);
        EventCategoryEntity updatedEventCategoryEntity = eventCategoryRepository.save(eventCategoryEntity);
        EventCategoryModel updatedEventCategoryModel = eventCategoryMapper.from(updatedEventCategoryEntity);

        LOGGER.info("update(...) = " + updatedEventCategoryModel);
        return updatedEventCategoryModel;
    }

    public void delete(EventCategoryModel eventCategoryModel) {
        LOGGER.info("delete(" + eventCategoryModel + ")");

        EventCategoryEntity eventCategoryEntity = eventCategoryMapper.from(eventCategoryModel);
        eventCategoryRepository.delete(eventCategoryEntity);

        LOGGER.info("delete(...) = " + eventCategoryModel);
    }
}
