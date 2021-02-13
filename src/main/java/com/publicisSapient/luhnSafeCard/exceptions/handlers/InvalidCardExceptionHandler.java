package com.publicisSapient.luhnSafeCard.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.publicisSapient.luhnSafeCard.exceptions.InvalidCardException;

@RestControllerAdvice
public class InvalidCardExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = InvalidCardException.class)
    public ResponseEntity<?> handleException(InvalidCardException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
