package com.example.insurance_calculator.core.repositories.get;

import com.example.insurance_calculator.core.domain.agreement.AgreementEntityDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GetAgreementRepository extends JpaRepository<AgreementEntityDomain, Long> {
    public Optional<AgreementEntityDomain> findByUuid(UUID uuid);
}
