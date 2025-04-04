package com.example.insurance_calculator.core.validations.get;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;

import java.util.Optional;

public interface GetCommandUUIDValidation {
    public Optional<ErrorDTO> validate(String uuid);
}
