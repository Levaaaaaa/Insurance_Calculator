package com.example.insurance_calculator.core.underwriting.calculators;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.underwriting.TravelRiskPremiumCalculator;
import com.example.insurance_calculator.core.underwriting.calculators.evacuation.TEPremiumCalculatorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelRiskPremiumCalculatorEvacuation implements TravelRiskPremiumCalculator {
    @Autowired
    private List<TEPremiumCalculatorComponent> calculators;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        if (agreement == null || person == null) {
            return BigDecimal.ZERO;
        }

        return calculators.stream().map(
                calculator -> calculator.calculatePremium(agreement, person)
        ).reduce(BigDecimal::multiply).get();
    }

    @Override
    public String getIc() {
        return "TRAVEL_EVACUATION";
    }
}
