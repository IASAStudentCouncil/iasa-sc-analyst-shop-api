package iasa.sc.site.Backend.exceptions.handlers;

import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {UnknownIdException.class})
    public ErrorResponse handleUnknownIdException(UnknownIdException e) {
        return new ErrorResponseImpl(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ErrorResponse handleValidationException(ValidationException e) {
        return new ErrorResponseImpl(e.getErrorCode(), e.getMessage());
    }
}