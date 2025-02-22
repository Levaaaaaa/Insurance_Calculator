package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.core.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.PersonInAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgreementPersonEntityRepository extends JpaRepository<PersonInAgreementEntity, Long> {

    @Query("SELECT pers FROM PersonInAgreementEntity pers " +
            "left join pers.person personDomain " +
            "where personDomain.personFirstName = :fname " +
            "AND personDomain.personLastName = :lname " +
            "AND personDomain.personIc = :ic")
    Optional<PersonInAgreementEntity> findByName(@Param("fname") String firstName,
                                                 @Param("lname") String lastName,
                                                 @Param("ic") UUID ic);

    List<PersonInAgreementEntity> findByAgreement(AgreementEntity agreementEntity);

}
