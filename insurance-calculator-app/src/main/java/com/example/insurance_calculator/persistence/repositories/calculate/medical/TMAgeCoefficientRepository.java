package com.example.insurance_calculator.persistence.repositories.calculate.medical;

import com.example.insurance_calculator.persistence.entities.calculate.medical.TMAgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TMAgeCoefficientRepository
        extends JpaRepository<TMAgeCoefficient, Long> {

    @Query("SELECT ac from TMAgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo >= :age")
    Optional<TMAgeCoefficient> findCoefficient(@Param("age") Integer age);

}
