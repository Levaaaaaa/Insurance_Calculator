package com.example.insurance_calculator.core.validations;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import com.example.insurance_calculator.core.util.ErrorCodeUtil;
import com.example.insurance_calculator.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil util;

    public ErrorDTO buildError(String errorCode) {
        return new ErrorDTO(errorCode, util.getErrorDescription(errorCode));
    }

    public List<ErrorDTO> buildError(String errorCode, List<Placeholder> placeholders) {
        return placeholders.stream().map(
                placeholder ->
                        buildError(errorCode, placeholder)
        ).toList();
    }

    public ErrorDTO buildError(String errorCode, Placeholder placeholder) {
        return new ErrorDTO(errorCode, util.getErrorDescription(errorCode, placeholder));
    }
}
