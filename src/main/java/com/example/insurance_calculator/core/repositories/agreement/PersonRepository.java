package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.core.domain.agreement.PersonDTODomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonDTODomain, Long> {

    @Query("SELECT p FROM PersonDTODomain p" +
            " WHERE p.personFirstName = :fn" +
            " AND p.personLastName = :lastName" +
            " AND p.personIc = :i")
    Optional<PersonDTODomain> findBy(@Param("fn") String firstName,
                                            @Param("lastName") String lastName,
                                            @Param("i") UUID ic
    );


}