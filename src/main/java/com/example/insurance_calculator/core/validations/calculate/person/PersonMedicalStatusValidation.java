package com.example.insurance_calculator.core.validations.calculate.person;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonMedicalStatusValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person, List<String> selectedRisks) {
        if (selectedRisks != null
                && selectedRisks.contains("TRAVEL_EVACUATION")
                && person != null
                &&
                (
                        person.getPersonMedicalStatus() == null
                )
        )
        {
            return Optional.of(errorFactory.buildError("ERROR_CODE_20"));
        }
        return Optional.empty();
    }
}
