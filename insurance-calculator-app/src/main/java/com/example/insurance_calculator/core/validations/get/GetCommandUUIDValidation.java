package com.example.insurance_calculator.core.validations.get;

import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

public interface GetCommandUUIDValidation {
    public Optional<ValidationErrorDTO> validate(String uuid);
}
