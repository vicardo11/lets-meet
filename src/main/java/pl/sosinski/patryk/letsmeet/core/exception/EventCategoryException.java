package pl.sosinski.patryk.letsmeet.core.exception;

public class EventCategoryException extends LetsMeetException{
    public EventCategoryException(String message) {
        super(message);
    }

    public EventCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
