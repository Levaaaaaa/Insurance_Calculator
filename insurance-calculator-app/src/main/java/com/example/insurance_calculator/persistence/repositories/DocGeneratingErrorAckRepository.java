package com.example.insurance_calculator.persistence.repositories;

import com.example.insurance_calculator.persistence.entities.DocGeneratorAckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocGeneratingErrorAckRepository extends JpaRepository<DocGeneratorAckEntity, Long> {
}
