package pl.sosinski.patryk.letsmeet.service;

import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.InterestNotFoundException;
import pl.sosinski.patryk.letsmeet.repository.InterestRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;
import pl.sosinski.patryk.letsmeet.service.mapper.InterestMapper;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class InterestService {

    private static final Logger LOGGER = Logger.getLogger(InterestService.class.getName());

    private final InterestRepository interestRepository;
    private final InterestMapper interestMapper;

    public InterestService(InterestRepository interestRepository, InterestMapper interestMapper) {
        this.interestRepository = interestRepository;
        this.interestMapper = interestMapper;
    }

    public List<InterestModel> list() {
        LOGGER.info("list()");
        List<InterestEntity> interestEntities = interestRepository.findAll();

        List<InterestModel> interestModels = interestMapper.fromEntities(interestEntities);

        LOGGER.info("list(...)");
        return interestModels;
    }

    public InterestModel create(InterestModel interestModel) {
        LOGGER.info("create(" + interestModel + ")");

        InterestEntity interestEntity = interestMapper.from(interestModel);
        InterestEntity savedInterestEntity = interestRepository.save(interestEntity);
        InterestModel savedInterestModel = interestMapper.from(savedInterestEntity);

        LOGGER.info("create(...) = " + savedInterestModel);
        return savedInterestModel;
    }

    public InterestModel read(Long interestId) throws InterestNotFoundException {
        LOGGER.info("read(" + interestId + ")");

        Optional<InterestEntity> optionalInterestEntity = interestRepository.findById(interestId);
        InterestEntity interestEntity = optionalInterestEntity.orElseThrow(
                () -> new InterestNotFoundException("Interest not found for id: " + interestId)
        );
        InterestModel interestModel = interestMapper.from(interestEntity);

        LOGGER.info("read(" + interestId + ")");
        return interestModel;
    }

    public InterestModel update(InterestModel interestModel) {
        LOGGER.info("update(" + interestModel + ")");

        InterestEntity interestEntity = interestMapper.from(interestModel);
        InterestEntity updatedInterestEntity = interestRepository.save(interestEntity);
        InterestModel updatedInterestModel = interestMapper.from(updatedInterestEntity);

        LOGGER.info("update(...) = " + updatedInterestModel);
        return updatedInterestModel;
    }

    public void delete(InterestModel interestModel) {
        LOGGER.info("delete(" + interestModel + ")");

        InterestEntity interestEntity = interestMapper.from(interestModel);
        interestRepository.delete(interestEntity);

        LOGGER.info("delete(...) = " + interestModel);
    }
}
