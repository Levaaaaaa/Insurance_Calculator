package com.example.insurance_calculator.core.repositories.calculate.cancellation;

import com.example.insurance_calculator.core.entities.calculate.cancellation.TCAgeCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCAgeCoefficientRepository extends JpaRepository<TCAgeCoefficientDomain, Long> {
    @Query("SELECT domain FROM TCAgeCoefficientDomain domain " +
            "WHERE domain.ageFrom <= :age " +
            "AND domain.ageTo >= :age")
    public Optional<TCAgeCoefficientDomain> findCoefficient(@Param("age") Integer age);
}
