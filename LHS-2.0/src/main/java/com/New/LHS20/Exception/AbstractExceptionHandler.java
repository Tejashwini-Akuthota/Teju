package com.New.LHS20.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

abstract class AbstractExceptionHandler {

    ResponseEntity<String> mapToResponseEntity(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(exception.getMessage(), status);
    }

}
