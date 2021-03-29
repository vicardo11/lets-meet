package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.InterestEntity;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
}
