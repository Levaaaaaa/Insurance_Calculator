package com.example.insurance_calculator.core.validations.calculate.person;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.calculate.TravelPersonFieldValidation;

import java.util.List;
import java.util.Optional;

public abstract class TravelPersonFieldValidationImpl implements TravelPersonFieldValidation {
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO person) {
        return null;
    }

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person, List<String> selectedRisks) {
        return this.validate(person);
    }
}
