package com.example.insurance_calculator.core.underwriting;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> calculators;

    public BigDecimal calculatePremiumForOneRisk(String riskIc, AgreementDTO agreement, PersonDTO person) {
        return findCalculatorByIc(riskIc).calculatePremium(agreement, person);
    }

    public List<RiskDTO> calculatePremiumForAllRisks(AgreementDTO agreement, PersonDTO person) {
        return agreement.getSelectedRisks().stream()
                .map(riskIc ->
                        new RiskDTO(riskIc, calculatePremiumForOneRisk(riskIc, agreement, person)))
                .toList();
    }
    public TravelRiskPremiumCalculator findCalculatorByIc(String riskIc) {
        for (TravelRiskPremiumCalculator calculator : calculators) {
            if (calculator.getIc().equals(riskIc)) {
                return calculator;
            }
        }
        throw new RuntimeException("Risk with ic = " + riskIc + " is not supported!");
    }
}
