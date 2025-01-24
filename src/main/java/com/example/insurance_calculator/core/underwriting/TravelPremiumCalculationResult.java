package com.example.insurance_calculator.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.insurance_calculator.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelPremiumCalculationResult {
    private BigDecimal totalPremium;
    private List<RiskDTO> risks;
}
