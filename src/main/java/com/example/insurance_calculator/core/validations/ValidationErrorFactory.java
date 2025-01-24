package com.example.insurance_calculator.core.validations;

import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.util.ErrorCodeUtil;
import com.example.insurance_calculator.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil util;

    public ValidationErrorDTO buildError(String errorCode) {
        return new ValidationErrorDTO(errorCode, util.getErrorDescription(errorCode));
    }

    public ValidationErrorDTO buildError(String errorCode, List<Placeholder> placeholders) {
        return new ValidationErrorDTO(errorCode, util.getErrorDescription(errorCode, placeholders));
    }
}
