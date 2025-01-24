package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateFromLessThanDateToValidation extends TravelAgreementFieldValidationImpl {
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        if (request.getAgreementDateTo() == null
                || request.getAgreementDateFrom() == null
                || request.getAgreementDateTo().before(new Date())
                || request.getAgreementDateFrom().before(new Date())
        ) {
            return Optional.empty();
        }
        return request.getAgreementDateFrom().after(request.getAgreementDateTo())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
