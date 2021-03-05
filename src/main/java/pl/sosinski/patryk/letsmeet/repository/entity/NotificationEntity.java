package pl.sosinski.patryk.letsmeet.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATIONS")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public NotificationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
