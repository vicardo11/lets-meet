package pl.sosinski.patryk.letsmeet.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sosinski.patryk.letsmeet.core.api.ErrorResponse;
import pl.sosinski.patryk.letsmeet.core.exception.LetsMeetException;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {LetsMeetException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handler(Exception exception) {
        LOGGER.info("handler(" + exception + ")");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        LOGGER.info("handler(...) = " + errorResponse);
        return errorResponse;
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse runtimeHandler(Exception exception) {
        LOGGER.info("runtimeHandler(" + exception + ")");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        LOGGER.info("runtimeHandler(...) = " + errorResponse);
        return errorResponse;
    }
}
