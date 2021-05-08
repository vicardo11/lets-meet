package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.EventNotFoundException;
import pl.sosinski.patryk.letsmeet.repository.EventRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.EventMapper;
import pl.sosinski.patryk.letsmeet.service.mapper.InterestMapper;
import pl.sosinski.patryk.letsmeet.web.model.EventModel;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventService {

    private static final Logger LOGGER = Logger.getLogger(EventService.class.getName());

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final InterestMapper interestMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, InterestMapper interestMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.interestMapper = interestMapper;
    }

    public List<EventModel> list() {
        LOGGER.info("list()");
        List<EventEntity> eventEntities = eventRepository.findAll();

        List<EventModel> eventModels = eventMapper.fromEntities(eventEntities);

        LOGGER.info("list(...)");
        return eventModels;
    }

    public List<EventModel> listByInterest(InterestModel interestModel) {
        LOGGER.info("listByInterest(" + interestModel + ")");
        InterestEntity interestEntity = interestMapper.from(interestModel);

        List<Long> ids = List.of(interestModel.getId());
        List<EventEntity> eventEntitiesByInterest = eventRepository.findByInterestsIdIn(ids);
        List<EventModel> eventModelsByInterest = eventMapper.fromEntities(eventEntitiesByInterest);

        LOGGER.info("listByInterest(...) = " + eventModelsByInterest);
        return eventModelsByInterest;
    }

    public EventModel create(EventModel eventModel) {
        LOGGER.info("create(" + eventModel + ")");

        EventEntity eventEntity = eventMapper.from(eventModel);
        EventEntity savedEventEntity = eventRepository.save(eventEntity);
        EventModel savedEventModel = eventMapper.from(savedEventEntity);

        LOGGER.info("create(...) = " + savedEventModel);
        return savedEventModel;
    }

    public EventModel read(Long eventId) throws EventNotFoundException {
        LOGGER.info("read(" + eventId + ")");

        Optional<EventEntity> optionalEventEntity = eventRepository.findById(eventId);
        EventEntity eventEntity = optionalEventEntity.orElseThrow(
                () -> new EventNotFoundException("Event not found for id: " + eventId)
        );
        EventModel eventModel = eventMapper.from(eventEntity);

        LOGGER.info("read(" + eventId + ")");
        return eventModel;
    }

    public EventModel update(EventModel eventModel) {
        LOGGER.info("update(" + eventModel + ")");

        EventEntity eventEntity = eventMapper.from(eventModel);
        EventEntity updatedEventEntity = eventRepository.save(eventEntity);
        EventModel updatedEventModel = eventMapper.from(updatedEventEntity);

        LOGGER.info("update(...) = " + updatedEventModel);
        return updatedEventModel;
    }

    public void delete(Long eventId) {
        LOGGER.info("delete(" + eventId + ")");

        eventRepository.deleteById(eventId);

        LOGGER.info("delete(...)");
    }
}
