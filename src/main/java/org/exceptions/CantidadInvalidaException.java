package org.exceptions;

public class CantidadInvalidaException extends RuntimeException {
    public CantidadInvalidaException(String message) {
        super(message);
    }
}
