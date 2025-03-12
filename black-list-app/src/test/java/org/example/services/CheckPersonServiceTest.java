package org.example.services;

import org.example.dto.BlackListedPersonDTO;
import org.example.persistence.entities.BlackListedPersonEntity;
import org.example.persistence.repostitories.BlackListedPersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CheckPersonServiceTest {
    @InjectMocks
    private CheckPersonServiceImpl checkService;

    @Mock
    private BlackListedPersonRepository personRepository;

    private static BlackListedPersonDTO personDTO;


    @BeforeAll
    public static void init() {
        personDTO = new BlackListedPersonDTO(
                "first_name",
                "last_name",
                "11111111-1111-1111-1111-111111111111",
                false);
    }

    @Test
    public void existingPerson() {
        when(personRepository.findByFirstNameAndLastNameAndPersonCode(
                personDTO.getFirstName(),
                personDTO.getLastName(),
                UUID.fromString(personDTO.getPersonCode())))
                .thenReturn(Optional.of(new BlackListedPersonEntity()));

        BlackListedPersonDTO result = checkService.checkPerson(personDTO);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getPersonCode(), result.getPersonCode());
        assertTrue(result.isBlackListed());
    }

    @Test
    public void notExistingFirstName() {
        when(personRepository.findByFirstNameAndLastNameAndPersonCode(
                "some_another_first_name",
                personDTO.getLastName(),
                UUID.fromString(personDTO.getPersonCode())))
                .thenReturn(Optional.of(new BlackListedPersonEntity()));

        BlackListedPersonDTO result = checkService.checkPerson(personDTO);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getPersonCode(), result.getPersonCode());
        assertFalse(result.isBlackListed());
    }

    @Test
    public void notExistingLastName() {
        when(personRepository.findByFirstNameAndLastNameAndPersonCode(
                personDTO.getFirstName(),
                "some_another_last_name",
                UUID.fromString(personDTO.getPersonCode())))
                .thenReturn(Optional.of(new BlackListedPersonEntity()));

        BlackListedPersonDTO result = checkService.checkPerson(personDTO);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getPersonCode(), result.getPersonCode());
        assertFalse(result.isBlackListed());
    }

    @Test
    public void notExistingPersonCode() {
        when(personRepository.findByFirstNameAndLastNameAndPersonCode(
                personDTO.getFirstName(),
                personDTO.getLastName(),
                UUID.fromString("00000000-0000-0000-0000-000000000000")))
                .thenReturn(Optional.of(new BlackListedPersonEntity()));

        BlackListedPersonDTO result = checkService.checkPerson(personDTO);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getPersonCode(), result.getPersonCode());
        assertFalse(result.isBlackListed());
    }
}
