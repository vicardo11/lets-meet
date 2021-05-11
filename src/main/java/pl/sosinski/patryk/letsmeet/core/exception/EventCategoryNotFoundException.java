package pl.sosinski.patryk.letsmeet.core.exception;

public class EventCategoryNotFoundException extends EventCategoryException {
    public EventCategoryNotFoundException(String message) {
        super(message);
    }

    public EventCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
