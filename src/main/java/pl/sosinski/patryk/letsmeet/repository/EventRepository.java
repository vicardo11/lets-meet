package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
