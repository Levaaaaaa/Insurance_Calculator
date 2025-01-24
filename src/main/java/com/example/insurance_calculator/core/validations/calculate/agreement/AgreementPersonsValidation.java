package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgreementPersonsValidation extends TravelAgreementFieldValidationImpl {
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return request.getPersons() == null || request.getPersons().isEmpty()
                ? Optional.of(errorFactory.buildError("ERROR_CODE_17"))
                : Optional.empty();
    }
}
