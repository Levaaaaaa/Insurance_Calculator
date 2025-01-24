package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class AgreementCostValidation extends TravelAgreementFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        if (agreement.getCost() == null || agreement.getCost().equals(BigDecimal.ZERO)) {
            return Optional.of(errorFactory.buildError("ERROR_CODE_19"));
        }

        return Optional.empty();
    }
}
