package pl.sosinski.patryk.letsmeet.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryModel {

    private Long id;
    private String name;
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<EventModel> events = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
