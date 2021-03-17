package pl.sosinski.patryk.letsmeet.core.exception;

public class EventException extends LetsMeetException{
    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }
}
