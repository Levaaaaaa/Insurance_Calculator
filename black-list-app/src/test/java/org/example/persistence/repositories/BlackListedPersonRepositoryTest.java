package org.example.persistence.repositories;

import org.example.persistence.entities.BlackListedPersonEntity;
import org.example.persistence.repostitories.BlackListedPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BlackListedPersonRepositoryTest {
    @Autowired
    private BlackListedPersonRepository repository;

    private static String firstName, lastName;
    private static UUID personCode;

    @BeforeEach
    public void init() {
        firstName = "first_name";
        lastName = "last_name";
        personCode = UUID.fromString("11111111-1111-1111-1111-111111111111");
    }
    @Test
    public void notNull() {
        assertNotNull(repository);
    }

    @Test
    public void findExistingPerson() {
        Optional<BlackListedPersonEntity> optional = repository.findByFirstNameAndLastNameAndPersonCode(
                firstName,
                lastName,
                personCode
        );
        assertTrue(optional.isPresent());
        assertEquals(firstName, optional.get().getFirstName());
        assertEquals(lastName, optional.get().getLastName());
        assertEquals(personCode, optional.get().getPersonCode());
    }

    @Test
    public void notExistingFirstName() {
        firstName = "not_existing_fist_name";
        Optional<BlackListedPersonEntity> optional = repository.findByFirstNameAndLastNameAndPersonCode(
                firstName,
                lastName,
                personCode
        );
        assertTrue(optional.isEmpty());
    }

    @Test
    public void notExistingLastName() {
        lastName = "not_existing_last_name";
        Optional<BlackListedPersonEntity> optional = repository.findByFirstNameAndLastNameAndPersonCode(
                firstName,
                lastName,
                personCode
        );
        assertTrue(optional.isEmpty());
    }

    @Test
    public void notExistingPersonCode() {
        personCode = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Optional<BlackListedPersonEntity> optional = repository.findByFirstNameAndLastNameAndPersonCode(
                firstName,
                lastName,
                personCode
        );
        assertTrue(optional.isEmpty());
    }
}
