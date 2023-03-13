package iasa.sc.site.Backend.exceptions;

import lombok.Getter;

@Getter
public class UnknownIdException extends RuntimeException {
    private final String errorCode = "400";

    public UnknownIdException(String message) {
        super(message);
    }
}
