package com.hummersoft.eira.exception.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hummersoft.eira.exception.EiraException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EiraException.class)
    public ResponseEntity<Message> internalServerErrorHandler(EiraException e) {
        Message msg = new Message(e.getStatusCode(), e.getMessage());
        return ResponseEntity.status(e.getStatusCode() != 0 ? e.getStatusCode() : INTERNAL_SERVER_ERROR.value())
                .body(msg);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Message> badRequest(Exception e) {
        Message msg = new Message(BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(BAD_REQUEST.value()).body(msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Message> runtimeExceptionHandler(RuntimeException e) {
        Message msg = new Message(INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR.value()).body(msg);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Message> DataIntegrityViolationException(DataIntegrityViolationException e) {
        Message msg = new Message(BAD_REQUEST.value(), Objects.requireNonNull(e.getRootCause()).getMessage());
        return ResponseEntity.status(BAD_REQUEST.value()).body(msg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Message msg = new Message(BAD_REQUEST.value(), errors.toString());
        return ResponseEntity.status(BAD_REQUEST.value()).body(msg);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        // Customize the response as needed
        return ResponseEntity.status(401).body(e.getMessage());
    }

}
