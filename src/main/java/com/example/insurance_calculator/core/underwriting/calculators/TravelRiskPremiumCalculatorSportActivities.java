package com.example.insurance_calculator.core.underwriting.calculators;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelRiskPremiumCalculatorSportActivities implements TravelRiskPremiumCalculator {
    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getIc() {
        return "TRAVEL_SPORT_ACTIVITIES";
    }
}
