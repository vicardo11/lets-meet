package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.ParticipantEntity;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
