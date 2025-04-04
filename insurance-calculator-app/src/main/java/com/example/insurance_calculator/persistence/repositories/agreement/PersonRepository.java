package com.example.insurance_calculator.persistence.repositories.agreement;

import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p" +
            " WHERE p.personFirstName = :fn" +
            " AND p.personLastName = :lastName" +
            " AND p.personIc = :i")
    Optional<PersonEntity> findBy(@Param("fn") String firstName,
                                  @Param("lastName") String lastName,
                                  @Param("i") UUID ic
    );
    Optional<PersonEntity> findByPersonIc(UUID ic);


}
