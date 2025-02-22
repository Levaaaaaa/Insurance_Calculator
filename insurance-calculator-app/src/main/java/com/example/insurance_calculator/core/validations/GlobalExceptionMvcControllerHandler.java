package com.example.insurance_calculator.core.validations;

import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice("com.example.insurance_calculator.controllers.mvc")
public class GlobalExceptionMvcControllerHandler {
    @Autowired
    private ValidationErrorFactory errorFactory;

    @ExceptionHandler(value = MethodArgumentNotValidException.class, produces = MediaType.TEXT_HTML_VALUE)
    //@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String handleValidationExceptionMvc(MethodArgumentNotValidException e, Model model) {
        HashMap<String, ValidationErrorDTO> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), errorFactory.buildError(err.getDefaultMessage()));
        });

        model.addAttribute("request", new TravelCalculatePremiumRequestV2());
        model.addAttribute("response", new TravelCalculatePremiumResponseV2());
        model.addAttribute("validationErrors", errors);
        return "travel-calculate-premium-v2";

    }
}
