package com.example.insurance_calculator.core.blacklist;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class BlackListExceptionHandler {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @ExceptionHandler(WebClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public List<ErrorDTO> handleWebClientException(WebClientException e, WebRequest request) {
        log.error(e.getMessage());
        return List.of(errorFactory.buildError("ERROR_CODE_26"));
    }
}
