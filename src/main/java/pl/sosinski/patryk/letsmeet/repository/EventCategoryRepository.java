package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;

public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Long> {
}
