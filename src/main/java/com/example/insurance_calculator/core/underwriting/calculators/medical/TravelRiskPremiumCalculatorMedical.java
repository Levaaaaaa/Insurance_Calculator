package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {

    @Autowired
    private List<TravelRiskPremiumCalculatorMedicalComponent> calculators;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal result = BigDecimal.ONE;
        for (TravelRiskPremiumCalculatorMedicalComponent c : calculators) {
            result = result.multiply(c.calculatePremium(agreement, person));
        }

        return result;

    }
    @Override
    public String getIc() {
        return "TRAVEL_MEDICAL";
    }
}
