package org.example.validators;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.example.validators.error_factory.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalValidationExceptionHandler {
    @Autowired
    private ValidationErrorFactory errorFactory;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public List<ValidationError> validationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream().map(violation -> {
            return errorFactory.buildError(violation.getMessage());
        }).toList();
    }
}
