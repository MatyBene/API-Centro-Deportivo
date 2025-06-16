package com.utn.API_CentroDeportivo.model.exception;

public class MaxCapacityException extends RuntimeException {
    public MaxCapacityException(String message) {
        super(message);
    }
}
