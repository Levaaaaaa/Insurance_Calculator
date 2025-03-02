package com.example.insurance_calculator.persistence.repositories.agreement;

import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementWithRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgreementRiskEntityRepository extends JpaRepository<AgreementWithRiskEntity, Long> {
 //   Optional<AgreementRiskEntityDomain> findByRiskIc(String riskIc);

    @Query("SELECT risk FROM AgreementWithRiskEntity risk " +
            "LEFT JOIN risk.agreement a " +
            "where risk.riskIc = :ic " +
            "and a = :agr")
    Optional<AgreementWithRiskEntity> findByIcAndAgreement(@Param("ic")String riskIc,
                                                           @Param("agr") AgreementEntity agreement);
}
