package com.example.insurance_calculator.core.underwriting.calculators;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.underwriting.TravelRiskPremiumCalculator;
import com.example.insurance_calculator.core.underwriting.calculators.cancellation.TravelRiskPremiumCalculatorCancellationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelRiskPremiumCalculatorCancellation implements TravelRiskPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculatorCancellationComponent> components;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {

        return components.stream()
                .map(
                        component ->
                                component.calculatePremium(agreement, person)
                )
                .reduce(BigDecimal::add)
                .get();
    }

    @Override
    public String getIc() {
        return "TRAVEL_CANCELLATION";
    }
}
