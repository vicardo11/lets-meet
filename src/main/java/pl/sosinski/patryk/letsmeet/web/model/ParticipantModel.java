package pl.sosinski.patryk.letsmeet.web.model;

import lombok.Data;

@Data
public class ParticipantModel {

    private Long id;
    private String firstName;
    private String lastName;
    private short age;
    private String email;
    private String password;
}
