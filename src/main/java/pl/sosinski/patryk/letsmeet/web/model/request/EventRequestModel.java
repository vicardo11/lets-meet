package pl.sosinski.patryk.letsmeet.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestModel {

    private Long id;
    @Pattern(regexp = "^[A-Z].{3,}", message = "Event name must start with capital letter and has minimum 3 characters")
    private String name;
    @NotNull
    private String hostId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "Must be in the future")
    private LocalDateTime dateTime = LocalDateTime.now();
    @URL
    @NotBlank(message = "Can't be empty")
    private String url;
    @Range(min = 1, max = 1440)
    private int durationInMinutes;
    @NotNull
    private String categoryId;
}
