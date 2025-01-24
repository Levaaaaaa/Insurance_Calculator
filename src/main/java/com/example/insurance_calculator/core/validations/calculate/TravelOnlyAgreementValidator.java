package com.example.insurance_calculator.core.validations.calculate;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelOnlyAgreementValidator {
    public List<ValidationErrorDTO> validate(AgreementDTO agreement);

}
