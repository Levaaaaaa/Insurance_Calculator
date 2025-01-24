package com.example.insurance_calculator.core.underwriting.calculators.evacuation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TEPremiumCalculatorComponent {
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person);
}
