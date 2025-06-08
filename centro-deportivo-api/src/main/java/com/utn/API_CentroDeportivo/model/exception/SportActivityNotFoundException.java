package com.utn.API_CentroDeportivo.model.exception;

public class SportActivityNotFoundException extends RuntimeException {
    public SportActivityNotFoundException(String message) {
        super(message);
    }
}
