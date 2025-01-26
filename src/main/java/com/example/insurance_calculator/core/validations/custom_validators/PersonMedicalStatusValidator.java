package com.example.insurance_calculator.core.validations.custom_validators;

import com.example.insurance_calculator.core.api.dto.v2.PersonRequestV2DTO;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.MedicalStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonMedicalStatusValidator implements ConstraintValidator<MedicalStatus, PersonRequestV2DTO> {

    @Override
    public void initialize(MedicalStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonRequestV2DTO request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getSelectedRisks() == null
                || !request.getSelectedRisks().contains("TRAVEL_EVACUATION")
                || (request.getPersonMedicalStatus() != null
                && request.getPersonMedicalStatus().isBlank());
    }
}
