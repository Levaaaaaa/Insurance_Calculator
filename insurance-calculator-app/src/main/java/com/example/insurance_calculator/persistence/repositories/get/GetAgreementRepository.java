package com.example.insurance_calculator.persistence.repositories.get;

import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GetAgreementRepository extends JpaRepository<AgreementEntity, Long> {
    public Optional<AgreementEntity> findByUuid(UUID uuid);
}
