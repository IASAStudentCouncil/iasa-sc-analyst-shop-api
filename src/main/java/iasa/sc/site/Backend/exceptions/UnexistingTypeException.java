package iasa.sc.site.Backend.exceptions;

import lombok.Getter;

@Getter
public class UnexistingTypeException extends RuntimeException {
    private final String errorCode = "404";

    public UnexistingTypeException() {
        super("This type does not exist");
    }

    public UnexistingTypeException(String type) {
        super("Type " + type + " does not exist");
    }
}
