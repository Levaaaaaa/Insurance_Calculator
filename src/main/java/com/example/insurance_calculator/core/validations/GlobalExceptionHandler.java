package com.example.insurance_calculator.core.validations;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.util.Placeholder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Placeholder> placeholders = e.getBindingResult().getFieldErrors().stream()
                .filter(error -> error.getRejectedValue() != null)
                .map(error ->
                {
                    String placeholderValue = error.getRejectedValue().toString();
                    String placeholderName;
                    if (error.getField().matches("selectedRisks\\[\\d+]")) {
                        placeholderName = "NOT_EXISTING_RISK_TYPE";
                    }
                    else if (error.getField().matches("persons\\[\\d+].medicalRiskLimitLevel")) {
                        placeholderName = "NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL";
                    }
                    else if ("country".equals(error.getField())) {
                        placeholderName = "NOT_SUPPORTED_COUNTRY";
                    }
                    else {
                        placeholderName = "NOT_EXISTING_PLACEHOLDER_NAME";
                    }

                    return new Placeholder(placeholderName, placeholderValue);
                })
                .toList();

        if (placeholders.isEmpty()) {
            return e.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(
                            error ->
                                    errorFactory.buildError(
                                            error.getDefaultMessage()
                                    )
                    )
                    .toList();
        }
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .distinct()
                .flatMap(
                        errorCode -> errorFactory
                                .buildError(
                                    errorCode,
                                    placeholders
                                )
                                .stream()
                ).collect(Collectors.toList());
    }
}
