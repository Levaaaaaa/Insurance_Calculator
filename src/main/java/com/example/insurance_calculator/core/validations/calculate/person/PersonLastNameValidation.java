package com.example.insurance_calculator.core.validations.calculate.person;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonLastNameValidation extends TravelPersonFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return (person.getPersonLastName() == null || person.getPersonLastName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }
}