package com.example.insurance_calculator.core.util;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

public class GeneratePersonIc {
    public static List<PersonDTO> generatePersonIcs(AgreementDTO agreement) {
        if (agreement.getPersons() == null || agreement.getPersons().isEmpty()) {
            return agreement.getPersons();
        }
        for (PersonDTO person : agreement.getPersons()) {
            person.setPersonIc(UUID.randomUUID());
        }

        return agreement.getPersons();
    }
}
