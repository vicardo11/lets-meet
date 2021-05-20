package pl.sosinski.patryk.letsmeet.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.core.exception.EmailAlreadyExistsException;
import pl.sosinski.patryk.letsmeet.web.model.ParticipantModel;
import pl.sosinski.patryk.letsmeet.web.model.request.ParticipantRequestModel;

import java.util.logging.Logger;

@Service
public class ParticipantManagerService {

    private final Logger LOGGER = Logger.getLogger(ParticipantManagerService.class.getName());

    private final ParticipantService participantService;
    private final PasswordEncoder passwordEncoder;

    public ParticipantManagerService(ParticipantService participantService, PasswordEncoder passwordEncoder) {
        this.participantService = participantService;
        this.passwordEncoder = passwordEncoder;
    }

    public ParticipantModel create(ParticipantRequestModel participantRequestModel) throws EmailAlreadyExistsException {
        LOGGER.info("create(" + participantRequestModel + ")");
        ParticipantModel participantModel = new ParticipantModel();

        participantModel.setEmail(participantRequestModel.getEmail());
        participantModel.setPassword(passwordEncoder.encode(participantRequestModel.getPassword()));
        participantModel.setFirstName(participantRequestModel.getFirstName());
        participantModel.setLastName(participantRequestModel.getLastName());

        ParticipantModel createdParticipantModel = participantService.registerNewParticipant(participantModel);
        LOGGER.info("create(...) = " + createdParticipantModel);

        return createdParticipantModel;
    }
}
