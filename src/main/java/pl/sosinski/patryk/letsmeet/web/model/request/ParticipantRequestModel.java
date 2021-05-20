package pl.sosinski.patryk.letsmeet.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRequestModel {

    @Email(message = "Not an email")
    private String email;
    @NotNull
    @NotEmpty(message = "Can't be empty")
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
