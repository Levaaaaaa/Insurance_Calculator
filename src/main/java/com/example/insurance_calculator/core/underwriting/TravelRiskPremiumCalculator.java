package com.example.insurance_calculator.core.underwriting;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);
    String getIc();
}
