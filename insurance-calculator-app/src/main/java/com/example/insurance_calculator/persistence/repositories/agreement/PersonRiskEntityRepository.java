package com.example.insurance_calculator.persistence.repositories.agreement;

import com.example.insurance_calculator.persistence.entities.agreement.PersonInAgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.PersonsRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRiskEntityRepository extends JpaRepository<PersonsRiskEntity, Long> {

    @Query("SELECT risk FROM PersonsRiskEntity risk " +
            "LEFT JOIN risk.person pers " +
            "WHERE risk.riskIc = :ic " +
            "AND pers = :p")
    public Optional<PersonsRiskEntity> findByIcAndPerson(@Param("ic") String ic,
                                                         @Param("p") PersonInAgreementEntity person);
}
