package com.example.insurance_calculator.core.validations.calculate;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {
    public Optional<ValidationErrorDTO> validate(PersonDTO person);
    public List<ValidationErrorDTO> validateList(PersonDTO person);

    public Optional<ValidationErrorDTO> validate(PersonDTO person, List<String> selectedRisks);
}
