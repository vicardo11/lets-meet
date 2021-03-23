package pl.sosinski.patryk.letsmeet.core.exception;

public class ParticipantNotFoundException extends ParticipantException{
    public ParticipantNotFoundException(String message) {
        super(message);
    }

    public ParticipantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
