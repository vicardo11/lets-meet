package pl.sosinski.patryk.letsmeet.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;
import pl.sosinski.patryk.letsmeet.web.model.InterestModel;

import java.util.logging.Logger;

@Component
public class InterestMapper {

    private static final Logger LOGGER = Logger.getLogger(InterestMapper.class.getName());

    public InterestEntity from(InterestModel interestModel) {
        LOGGER.info("from(" + interestModel + ")");

        ModelMapper modelMapper = new ModelMapper();
        InterestEntity interestEntity = modelMapper.map(interestModel, InterestEntity.class);

        LOGGER.info("from(...)=" + interestEntity);
        return interestEntity;
    }

    public InterestModel from(InterestEntity interestEntity){
        LOGGER.info("from(" + interestEntity + ")");

        ModelMapper modelMapper = new ModelMapper();
        InterestModel interestModel = modelMapper.map(interestEntity, InterestModel.class);

        LOGGER.info("from(...)=" + interestModel);

        return interestModel;
    }
}
