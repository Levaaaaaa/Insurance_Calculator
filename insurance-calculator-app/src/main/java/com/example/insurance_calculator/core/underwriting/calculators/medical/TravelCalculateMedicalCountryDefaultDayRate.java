package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.calculate.medical.CountryDefaultDayRate;
import com.example.insurance_calculator.persistence.repositories.calculate.medical.CountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class TravelCalculateMedicalCountryDefaultDayRate implements TravelRiskPremiumCalculatorMedicalComponent{
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        Optional<CountryDefaultDayRate> optional = cddrRepository.findByCountryIc(agreement.getCountry());
        CountryDefaultDayRate cddr = optional.get();
        return cddr.getDefaultDayRate();
    }
}
