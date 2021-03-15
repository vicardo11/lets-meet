package pl.sosinski.patryk.letsmeet.core.exception;

public class NotificationNotFoundException extends NotificationException {

    public NotificationNotFoundException(String message) {
        super(message);
    }

    public NotificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
