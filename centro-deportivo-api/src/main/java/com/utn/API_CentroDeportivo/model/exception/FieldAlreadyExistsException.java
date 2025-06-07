package com.utn.API_CentroDeportivo.model.exception;

public class FieldAlreadyExistsException extends RuntimeException {
    private final String field; // Nuevo atributo para el nombre del campo

    public FieldAlreadyExistsException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
