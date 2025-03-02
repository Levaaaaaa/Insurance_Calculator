package com.example.insurance_calculator.core.underwriting.calculators.cancellation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.repositories.calculate.cancellation.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelCalculateCostCoefficientCancellation implements TravelRiskPremiumCalculatorCancellationComponent{
    @Autowired
    private TravelCostCoefficientRepository travelCostCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return travelCostCoefficientRepository.findCoefficientByCost(agreement.getCost())
                .get().getCoefficient();
    }
}
