package com.example.insurance_calculator.core.repositories.calculate.cancellation;

import com.example.insurance_calculator.core.domain.calculate.cancellation.TCCountrySafetyRatingCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafetyRatingRepository extends JpaRepository<TCCountrySafetyRatingCoefficientDomain, Long> {
    public Optional<TCCountrySafetyRatingCoefficientDomain> findByCountry(String country);
}