package com.example.insurance_calculator.services.agreement;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class PersonEntityService {
    @Autowired
    private PersonRepository personRepository;


    public PersonEntity getPersonEntity(PersonDTO person, AgreementEntity agreement) {
        Optional<PersonEntity> entity = personRepository.findBy(
                person.getPersonFirstName(),
                person.getPersonLastName(),
                person.getPersonIc()
        );
        if (entity.isPresent()) {
            return entity.get();
        }
        PersonEntity result = new PersonEntity();
        result.setPersonFirstName(person.getPersonFirstName());
        result.setPersonLastName(person.getPersonLastName());


        result.setPersonIc(person.getPersonIc());
        result.setPersonBirthDate(new Date(person.getPersonBirthDate().getTime()));
        personRepository.save(result);
        return result;
    }
}
