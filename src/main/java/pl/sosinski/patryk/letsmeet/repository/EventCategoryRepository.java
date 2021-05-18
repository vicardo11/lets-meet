package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventCategoryEntity;

import java.util.List;

public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Long> {

    List<EventCategoryEntity> findAllByOrderByName();
}
