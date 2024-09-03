package com.user.demo.domain.exception;

public class UserNotOfLegalAge extends RuntimeException {
    public UserNotOfLegalAge(String message) {
        super(message);
    }
}
