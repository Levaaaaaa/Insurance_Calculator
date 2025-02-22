package com.example.insurance_calculator.core.repositories.calculate.evacuation;

import com.example.insurance_calculator.core.entities.calculate.evacuation.TEMedicalCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TEMedicalCoefficientRepository extends JpaRepository<TEMedicalCoefficientDomain, Long> {
    public Optional<TEMedicalCoefficientDomain> findByIc(String ic);
}
