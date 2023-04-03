package iasa.sc.site.Backend.exceptions;

import lombok.Getter;

@Getter
public class UnexistingImageException extends RuntimeException {
    private final String errorCode = "400";

    public UnexistingImageException() {
        super("This image does not exist in blob storage");
    }

    public UnexistingImageException(String message) {
        super(message);
    }
}
