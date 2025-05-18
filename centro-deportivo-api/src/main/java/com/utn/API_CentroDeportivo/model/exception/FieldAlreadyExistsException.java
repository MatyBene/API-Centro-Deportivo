package com.utn.API_CentroDeportivo.model.exception;

public class FieldAlreadyExistsException extends RuntimeException {
    public FieldAlreadyExistsException(String message) {
        super(message);
    }
}
