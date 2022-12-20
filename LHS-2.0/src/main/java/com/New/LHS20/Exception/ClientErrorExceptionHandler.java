package com.New.LHS20.Exception;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
class ClientErrorExceptionHandler extends AbstractExceptionHandler {

    private final ErrorsExtractor errorsExtractor;

    @Autowired
    ClientErrorExceptionHandler(ErrorsExtractor errorsExtractor) {
        this.errorsExtractor = errorsExtractor;
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    ResponseEntity<String> handleResourceDoesNotExist(ResourceDoesNotExistException exception) {
        return mapToResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
ResponseEntity<String> handleResourceAlreadyExists(ResourceAlreadyExistsException exception) {
        return mapToResponseEntity(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> handleInvalidDataInController(MethodArgumentNotValidException exception) {
        Map<String, String> errorsMap = errorsExtractor.extractErrors(exception);
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

}
