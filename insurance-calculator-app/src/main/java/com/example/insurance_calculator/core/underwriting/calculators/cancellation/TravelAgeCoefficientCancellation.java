package com.example.insurance_calculator.core.underwriting.calculators.cancellation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.repositories.calculate.cancellation.TCAgeCoefficientRepository;
import com.example.insurance_calculator.core.util.CalculateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelAgeCoefficientCancellation implements TravelRiskPremiumCalculatorCancellationComponent{
    @Autowired
    private CalculateAgeUtil calculateAgeUtil;

    @Autowired
    private TCAgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return ageCoefficientRepository
                .findCoefficient(
                calculateAgeUtil
                        .calculateAge(
                        person.getPersonBirthDate()
                        )
                )
                .get().getCoefficient();
    }
}
