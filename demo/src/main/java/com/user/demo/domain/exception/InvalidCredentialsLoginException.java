package com.user.demo.domain.exception;

public class InvalidCredentialsLoginException extends RuntimeException {
    public InvalidCredentialsLoginException(String message) {
        super(message);
    }
}
