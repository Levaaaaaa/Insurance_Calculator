package com.example.insurance_calculator.core.validations.calculate.person;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//class will remove

//@Component
public class PersonIcValidation {//extends TravelPersonFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

  //  @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if (person.getPersonIc() == null) {
            return Optional.of(errorFactory.buildError("ERROR_CODE_16"));
        }
        return Optional.empty();
    }
}
