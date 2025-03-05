package org.example.persistence.repostitories;

import org.example.persistence.entities.BlackListedPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlackListedPersonRepository extends JpaRepository<BlackListedPersonEntity, Long> {
    public Optional<BlackListedPersonEntity> findByFirstNameAndLastNameAndPersonCode(
            String firstName,
            String lastName,
            UUID personCode);
}
