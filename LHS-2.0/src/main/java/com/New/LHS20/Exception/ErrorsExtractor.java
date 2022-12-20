package com.New.LHS20.Exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class ErrorsExtractor {

     Map<String, String> extractErrors(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        return extractErrorsFromObjectErrorList(objectErrors);
    }

    // Extract a map where:
    // - Key = Name of a field with validation errors
    // - Value = Error message associated with the validation error
    private Map<String, String> extractErrorsFromObjectErrorList(List<ObjectError> objectErrors) {
        return objectErrors.stream()
                .collect(Collectors.toMap(
                        this::extractNameOfFieldWithValidationErrors,
                        ObjectError::getDefaultMessage,
                        (firstValue, secondValue) -> firstValue   // If we get a duplicate, keep the first one
                ));
    }

    private String extractNameOfFieldWithValidationErrors(ObjectError error) {
        FieldError fieldError = (FieldError) error;
        String fieldName = fieldError.getField();
        return removeFieldNamesNesting(fieldName);
    }

    /*
     *  Nested objects will appear with a nested hierarchy separated by dots.
     *  This method removes the nesting and leaves only the last field name
     *
     *  Example:
     *  - Before conversion: person.name.lastname
     *  - After conversion:  lastname
     *
     */
    private String removeFieldNamesNesting(String fieldName) {
        int startOfLastFieldName = fieldName.lastIndexOf(".") + 1;
        int endOfLastFieldName = fieldName.length();
        return fieldName.substring(startOfLastFieldName, endOfLastFieldName);
    }


}
