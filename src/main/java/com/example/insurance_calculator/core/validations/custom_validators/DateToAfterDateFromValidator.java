package com.example.insurance_calculator.core.validations.custom_validators;

import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.DateToAfterDateFrom;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateToAfterDateFromValidator implements ConstraintValidator<DateToAfterDateFrom,TravelCalculatePremiumRequestV2> {
    @Override
    public void initialize(DateToAfterDateFrom constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TravelCalculatePremiumRequestV2 request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getAgreementDateTo() == null
                || request.getAgreementDateFrom() == null
                || request.getAgreementDateTo().before(new Date())
                || request.getAgreementDateFrom().before(new Date())
                || request.getAgreementDateFrom().before(request.getAgreementDateTo());
    }
}
