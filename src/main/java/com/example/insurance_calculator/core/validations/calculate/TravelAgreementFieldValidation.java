package com.example.insurance_calculator.core.validations.calculate;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelAgreementFieldValidation {
    public Optional<ValidationErrorDTO> validate(AgreementDTO request);
    public List<ValidationErrorDTO> validateList(AgreementDTO request);
}
