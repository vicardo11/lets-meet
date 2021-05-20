package pl.sosinski.patryk.letsmeet.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sosinski.patryk.letsmeet.repository.ParticipantRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;

import java.util.Collections;

@Service
public class LetsMeetUserDetailsService implements UserDetailsService {

    private final ParticipantRepository participantRepository;

    public LetsMeetUserDetailsService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ParticipantEntity participantEntity = participantRepository.findByEmail(username);
        if (participantEntity == null) throw new UsernameNotFoundException(username);

        return new User(
                participantEntity.getEmail(),
                participantEntity.getPassword(),
                Collections.emptyList()
        );
    }
}
