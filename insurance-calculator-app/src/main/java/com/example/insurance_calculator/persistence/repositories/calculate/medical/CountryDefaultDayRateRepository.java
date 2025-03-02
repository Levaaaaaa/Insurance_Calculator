package com.example.insurance_calculator.persistence.repositories.calculate.medical;

import com.example.insurance_calculator.persistence.entities.calculate.medical.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository
        extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);

}
