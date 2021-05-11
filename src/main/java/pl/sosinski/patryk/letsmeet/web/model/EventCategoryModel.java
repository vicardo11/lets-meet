package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryModel {

    private Long id;
    private String name;
    @Singular
    private final Set<EventModel> events = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
