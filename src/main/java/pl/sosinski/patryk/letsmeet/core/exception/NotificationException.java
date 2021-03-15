package pl.sosinski.patryk.letsmeet.core.exception;

public class NotificationException extends LetsMeetException {

    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
