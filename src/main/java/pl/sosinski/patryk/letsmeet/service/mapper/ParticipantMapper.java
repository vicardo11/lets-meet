package pl.sosinski.patryk.letsmeet.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;

import java.util.logging.Logger;

@Component
public class ParticipantMapper {

    private static final Logger LOGGER = Logger.getLogger(ParticipantMapper.class.getName());

    public ParticipantModel from(ParticipantEntity participantEntity) {
        LOGGER.info("from(" + participantEntity + ")");

        ModelMapper modelMapper = new ModelMapper();
        ParticipantModel participantModel = modelMapper.map(participantEntity, ParticipantModel.class);

        LOGGER.info("from(...) = " + participantModel);

        return participantModel;
    }

    public ParticipantEntity from(ParticipantModel participantModel) {
        LOGGER.info("from(" + participantModel + ")");

        ModelMapper modelMapper = new ModelMapper();
        ParticipantEntity participantEntity = modelMapper.map(participantModel, ParticipantEntity.class);

        LOGGER.info("from(...) = " + participantEntity);

        return participantEntity;
    }
}
