package org.example.persistence.repostitories;

import org.example.persistence.entities.BlackListedPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlackListedPersonRepository extends JpaRepository<BlackListedPersonEntity, Long> {
    @Query("SELECT person FROM BlackListedPersonEntity person " +
            "WHERE person.firstName = :fn " +
            "AND person.lastName = :ln " +
            "AND person.personCode = :pc")
    public Optional<BlackListedPersonEntity> findByFirstNameAndLastNameAndPersonCode(
            @Param("fn") String firstName,
            @Param("ln") String lastName,
            @Param("pc") UUID personCode);
    public Optional<BlackListedPersonEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
