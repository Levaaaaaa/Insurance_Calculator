package com.example.insurance_calculator.core.validations;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorDTO> handleValidationException(MethodArgumentNotValidException e, WebRequest request) throws JsonProcessingException {
        return e.getBindingResult()
                .getFieldErrors()
                .stream().map(
                        error -> errorFactory.buildError(
                                error.getDefaultMessage()
                        )
                )
                .collect(Collectors.toList());
    }
}
