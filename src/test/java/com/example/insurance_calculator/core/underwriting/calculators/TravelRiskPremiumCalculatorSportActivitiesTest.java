package com.example.insurance_calculator.core.underwriting.calculators;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static com.example.insurance_calculator.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static com.example.insurance_calculator.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class TravelRiskPremiumCalculatorSportActivitiesTest {
    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;

    @InjectMocks
    private TravelRiskPremiumCalculatorSportActivities calculator;

    @Test
    public void calculatePremiumIntegrationTest() {
        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_SPORT_ACTIVITIES")
                .build();
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}
