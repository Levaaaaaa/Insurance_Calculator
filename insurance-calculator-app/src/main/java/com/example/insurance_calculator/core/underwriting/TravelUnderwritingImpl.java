package com.example.insurance_calculator.core.underwriting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
//@AllArgsConstructor
class TravelUnderwritingImpl implements TravelUnderwriting{
    //@Autowired
    //private List<TravelRiskPremiumCalculator> riskCalculators;

    @Autowired
    private SelectedRisksPremiumCalculator calculator;
    /*
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {

        BigDecimal result = BigDecimal.ZERO;
        List<RiskDTO> risks = calculator.calculatePremiumForAllRisks(agreement, person);

        for (RiskDTO risk : risks) {
            result = result.add(risk.getPremium());
        }

        return result;
    }
    */
    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal result = BigDecimal.ZERO;
        List<RiskDTO> risks = calculator.calculatePremiumForAllRisks(agreement, person);

        for (RiskDTO risk : risks) {
            result = result.add(risk.getPremium());

        }

        return new TravelPremiumCalculationResult(result, risks);
    }
}
