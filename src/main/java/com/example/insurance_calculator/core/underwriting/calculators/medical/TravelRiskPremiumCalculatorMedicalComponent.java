package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculatorMedicalComponent {
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);
}