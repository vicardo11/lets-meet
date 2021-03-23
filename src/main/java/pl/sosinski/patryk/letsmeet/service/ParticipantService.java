package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;
import pl.sosinski.patryk.letsmeet.core.exception.ParticipantNotFoundException;
import pl.sosinski.patryk.letsmeet.repository.ParticipantRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.ParticipantMapper;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ParticipantService {

    private static final Logger LOGGER = Logger.getLogger(ParticipantService.class.getName());

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantService(ParticipantRepository participantRepository, ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.participantMapper = participantMapper;
    }

    public List<ParticipantModel> list() {
        LOGGER.info("list()");
        List<ParticipantEntity> participantEntities = participantRepository.findAll();

        List<ParticipantModel> participantModels = participantMapper.fromEntities(participantEntities);

        LOGGER.info("list() = " + participantModels);
        return participantModels;
    }

    public ParticipantModel create(ParticipantModel participantModel) {
        LOGGER.info("create(" + participantModel + ")");

        ParticipantEntity participantEntity = participantMapper.from(participantModel);
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        ParticipantModel savedParticipantModel = participantMapper.from(savedParticipantEntity);

        LOGGER.info("create(...) = " + savedParticipantModel);
        return savedParticipantModel;
    }

    public ParticipantModel read(Long participantId) throws LetsMeetException {
        LOGGER.info("read(" + participantId + ")");

        Optional<ParticipantEntity> optionalParticipantEntity = participantRepository.findById(participantId);
        ParticipantEntity participantEntity = optionalParticipantEntity.orElseThrow(
                () -> new ParticipantNotFoundException("Participant not found for id: " + participantId));

        ParticipantModel participantModel = participantMapper.from(participantEntity);

        LOGGER.info("read(...) = " + participantModel);
        return participantModel;
    }

    public ParticipantModel update(ParticipantModel participantModel) {
        LOGGER.info("update(" + participantModel + ")");

        ParticipantEntity participantEntity = participantMapper.from(participantModel);
        ParticipantEntity updatedParticipantEntity = participantRepository.save(participantEntity);
        ParticipantModel updatedParticipantModel = participantMapper.from(updatedParticipantEntity);

        LOGGER.info("update(...) = " + updatedParticipantModel);
        return updatedParticipantModel;
    }

    public void delete(ParticipantModel participantModel) {
        LOGGER.info("delete(" + participantModel + ")");

        ParticipantEntity participantEntity = participantMapper.from(participantModel);
        participantRepository.delete(participantEntity);

        LOGGER.info("delete(...) = " + participantModel);
    }
}
