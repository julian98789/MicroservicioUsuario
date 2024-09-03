package com.user.demo.domain.exception;

public class IdentificationAlreadyExistsException extends RuntimeException {
    public IdentificationAlreadyExistsException(String message) {
        super(message);
    }
}
