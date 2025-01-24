package com.example.insurance_calculator.core.underwriting.calculators.cancellation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.domain.calculate.cancellation.TCCountrySafetyRatingCoefficientDomain;
import com.example.insurance_calculator.core.repositories.calculate.cancellation.TCCountrySafetyRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
public class TCCountrySafetyRatingCoefficient implements TravelRiskPremiumCalculatorCancellationComponent{
    @Autowired
    private TCCountrySafetyRatingRepository csrRepository;
    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        Optional<TCCountrySafetyRatingCoefficientDomain> optional = csrRepository.findByCountry(agreement.getCountry());
        if (optional.isPresent()) {
            return agreement.getCost().divide(BigDecimal.valueOf(optional.get().getRating()), 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
