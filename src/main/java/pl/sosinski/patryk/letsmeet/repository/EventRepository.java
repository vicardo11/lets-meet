package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.EventEntity;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByCategoriesIdInOrderByDateTime(List<Long> ids);
    List<EventEntity> findByNameContainsIgnoreCaseOrderByDateTime(String eventName);
    List<EventEntity> findAllByOrderByDateTime();

}
