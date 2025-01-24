package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateFromInFutureValidation extends TravelAgreementFieldValidationImpl {
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        if (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().before(new Date())) {
            String errorCode = "ERROR_CODE_1";
            return Optional.of(errorFactory.buildError(errorCode));
        }

        return Optional.empty();
    }
}
