package com.example.insurance_calculator.core.services;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTOBuilder;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.PersonRepository;
import com.example.insurance_calculator.core.services.agreement.PersonEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class PersonEntityServiceTest {
    @InjectMocks
    private PersonEntityService service;

    @Mock
    private PersonRepository personRepository;


    PersonDTO personDTO;

    AgreementEntity agreementDomain = new AgreementEntity();

    @Test
    public void correctCreatePersonDomainTest() {
        String firstName = "FirstName";
        String lastName = "LastName";
        Date birthDate = Date.valueOf("2005-02-03");
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        personDTO = PersonDTOBuilder.createPersonDTO()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(birthDate)
                .withIc(ic)
                .build();
        when(personRepository.findBy(firstName, lastName, ic)).thenReturn(Optional.empty());
        agreementDomain.setId(0L);
        PersonEntity domain = service.getPersonEntity(personDTO, agreementDomain);
        assertEquals("", firstName, domain.getPersonFirstName());
        assertEquals("", lastName, domain.getPersonLastName());
        assertEquals("", birthDate, domain.getPersonBirthDate());
        assertEquals("", ic, domain.getPersonIc());
    }
}
