package iasa.sc.site.Backend.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String errorCode = "400";

    public ValidationException() {
        super("Something wrong with object`s fields");
    }

    public ValidationException(String message) {
        super(message);
    }
}