package com.example.insurance_calculator.persistence.repositories.calculate.evacuation;

import com.example.insurance_calculator.persistence.entities.calculate.evacuation.TEMedicalCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TEMedicalCoefficientRepository extends JpaRepository<TEMedicalCoefficientDomain, Long> {
    public Optional<TEMedicalCoefficientDomain> findByIc(String ic);
}
