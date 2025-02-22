package com.example.insurance_calculator.core.underwriting.calculators;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import com.example.insurance_calculator.core.api.dto.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static com.example.insurance_calculator.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static com.example.insurance_calculator.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class TravelRiskPremiumCalculatorThirdPartyLiabilityTest {
    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;

    @InjectMocks
    TravelRiskPremiumCalculatorThirdPartyLiability calculator;

    @Test
    public void calculatePremiumIntegrationTest() {
        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_THIRD_PARTY_LIABILITIES")
                .build();
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}
