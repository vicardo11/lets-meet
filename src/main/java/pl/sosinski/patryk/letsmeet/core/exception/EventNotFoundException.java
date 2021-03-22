package pl.sosinski.patryk.letsmeet.core.exception;

public class EventNotFoundException extends EventException{
    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
