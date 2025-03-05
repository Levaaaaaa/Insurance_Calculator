package org.example.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dto.BlackListedPersonDTO;
import org.example.persistence.entities.BlackListedPersonEntity;
import org.example.persistence.mapper.BlackListedPersonMapper;
import org.example.persistence.repostitories.BlackListedPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckPersonServiceImpl implements CheckPersonService{

    @Autowired
    BlackListedPersonRepository repository;

//    @Autowired
//    BlackListedPersonMapper personMapper;

    @Override
    public BlackListedPersonDTO checkPerson(BlackListedPersonDTO personDTO) {
        Optional<BlackListedPersonEntity> entityOptional = repository.findByFirstNameAndLastNameAndPersonCode(
                personDTO.getFirstName(),
                personDTO.getLastName(),
                UUID.fromString(personDTO.getPersonCode())
        );

        personDTO.setBlackListed(entityOptional.isPresent());
        return personDTO;
    }
}
