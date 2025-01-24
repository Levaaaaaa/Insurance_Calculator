package com.example.insurance_calculator.core.underwriting.calculators.evacuation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.domain.calculate.evacuation.TEMedicalCoefficientDomain;
import com.example.insurance_calculator.core.repositories.calculate.evacuation.TEMedicalCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TEMedicalCoefficientCalculator implements TEPremiumCalculatorComponent{
    @Autowired
    private TEMedicalCoefficientRepository medCoeffRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person) {
        Optional<TEMedicalCoefficientDomain> optional = medCoeffRepository.findByIc(person.getPersonMedicalStatus().name());
        return optional.isPresent()
                ? optional.get().getCoefficient()
                : BigDecimal.ONE;
    }
}
