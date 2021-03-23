package pl.sosinski.patryk.letsmeet.core.exception;

public class ParticipantException extends LetsMeetException{
    public ParticipantException(String message) {
        super(message);
    }

    public ParticipantException(String message, Throwable cause) {
        super(message, cause);
    }
}
