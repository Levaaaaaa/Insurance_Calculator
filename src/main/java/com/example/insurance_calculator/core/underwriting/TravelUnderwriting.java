package com.example.insurance_calculator.core.underwriting;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;

public interface TravelUnderwriting {
    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);//TravelCalculatePremiumResponse response);
    //public List<TravelRiskPremiumCalculator> getRiskCalculators();
}
