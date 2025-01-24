package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.core.domain.agreement.AgreementPersonEntityDomain;
import com.example.insurance_calculator.core.domain.agreement.PersonRiskEntityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRiskEntityRepository extends JpaRepository<PersonRiskEntityDomain, Long> {

    @Query("SELECT risk FROM PersonRiskEntityDomain risk " +
            "LEFT JOIN risk.person pers " +
            "WHERE risk.riskIc = :ic " +
            "AND pers = :p")
    public Optional<PersonRiskEntityDomain> findByIcAndPerson(@Param("ic") String ic,
                                                              @Param("p") AgreementPersonEntityDomain person);
}
