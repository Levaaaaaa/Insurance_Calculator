package com.example.insurance_calculator.core.underwriting.calculators.evacuation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.domain.calculate.evacuation.TEAgeCoefficientDomain;
import com.example.insurance_calculator.core.repositories.calculate.evacuation.TEAgeCoefficientRepository;
import com.example.insurance_calculator.core.util.CalculateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TEAgeCoefficientCalculator implements TEPremiumCalculatorComponent{
    @Autowired
    private TEAgeCoefficientRepository ageCoefficientRepository;

    @Autowired
    private CalculateAgeUtil calculateAgeUtil;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person) {
        Optional<TEAgeCoefficientDomain> optional =
                ageCoefficientRepository.findAgeCoefficient(
                        calculateAgeUtil.calculateAge(person.getPersonBirthDate())
                );

        return optional.isPresent() ? optional.get().getCoefficient() : BigDecimal.ONE;
    }
}
