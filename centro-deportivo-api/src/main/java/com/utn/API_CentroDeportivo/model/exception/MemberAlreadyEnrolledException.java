package com.utn.API_CentroDeportivo.model.exception;

public class MemberAlreadyEnrolledException extends RuntimeException {
    public MemberAlreadyEnrolledException(String message) {
        super(message);
    }
}
