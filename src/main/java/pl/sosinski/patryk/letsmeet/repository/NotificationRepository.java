package pl.sosinski.patryk.letsmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sosinski.patryk.letsmeet.repository.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
