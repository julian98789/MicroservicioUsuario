package com.user.demo.infrastructure.exception.global;

import com.user.demo.domain.exception.*;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsLoginException.class)
    public ResponseEntity<String> invalidCredentialsLoginException( InvalidCredentialsLoginException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<String> accountLockedException( AccountLockedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TooManyAttemptsException.class)
    public ResponseEntity<String> tooManyAttemptsException( TooManyAttemptsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotOfLegalAge.class)
    public ResponseEntity<String> userNotOfLegalAge(UserNotOfLegalAge ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> emailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IdentificationAlreadyExistsException.class)
    public ResponseEntity<String> identificationAlreadyExistsException(IdentificationAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        String errorMessage = e.getMessage();

        String cleanedMessage = extractErrorMessage(errorMessage);
        if (cleanedMessage == null || cleanedMessage.isEmpty()) {
            cleanedMessage = "Error desconocido";
        }
        HttpStatus status = HttpStatus.valueOf(e.status());
        return ResponseEntity.status(status).body(cleanedMessage);
    }

    private String extractErrorMessage(String errorMessage) {
        try {
            int startIndex = errorMessage.lastIndexOf('[') + 1;
            int endIndex = errorMessage.lastIndexOf(']');
            if (startIndex > 0 && endIndex > startIndex) {
                String message = errorMessage.substring(startIndex, endIndex).trim();
                if (message.startsWith(":")) {
                    message = message.substring(1).trim();
                }
                return message;
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return null;
    }
}

