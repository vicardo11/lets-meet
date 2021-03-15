package pl.sosinski.patryk.letsmeet.core.exception;

public class LetsMeetException extends Exception {

    public LetsMeetException(String message) {
        super(message);
    }

    public LetsMeetException(String message, Throwable cause) {
        super(message, cause);
    }
}
