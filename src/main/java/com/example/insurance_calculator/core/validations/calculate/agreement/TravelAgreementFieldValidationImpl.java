package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import com.example.insurance_calculator.core.validations.calculate.TravelAgreementFieldValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class TravelAgreementFieldValidationImpl implements TravelAgreementFieldValidation {
    @Autowired
    protected ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        return null;
    }
}
