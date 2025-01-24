package com.example.insurance_calculator.core.repositories.calculate.evacuation;


import com.example.insurance_calculator.core.domain.calculate.evacuation.TEAgeCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TEAgeCoefficientRepository extends JpaRepository<TEAgeCoefficientDomain, Long> {
    @Query("SELECT domain FROM TEAgeCoefficientDomain domain " +
            "WHERE domain.ageFrom <= :age AND domain.ageTo >= :age")
    public Optional<TEAgeCoefficientDomain> findAgeCoefficient(@Param("age") Integer age);
}
