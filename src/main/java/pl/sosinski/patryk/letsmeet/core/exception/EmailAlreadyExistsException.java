package pl.sosinski.patryk.letsmeet.core.exception;

public class EmailAlreadyExistsException extends ParticipantException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
