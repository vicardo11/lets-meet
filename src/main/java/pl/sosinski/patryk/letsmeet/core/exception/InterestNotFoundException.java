package pl.sosinski.patryk.letsmeet.core.exception;

public class InterestNotFoundException extends InterestException{
    public InterestNotFoundException(String message) {
        super(message);
    }

    public InterestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
