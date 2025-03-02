package com.example.insurance_calculator.core.underwriting.calculators.cancellation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.calculate.cancellation.TCCountrySafetyRatingCoefficientDomain;
import com.example.insurance_calculator.persistence.repositories.calculate.cancellation.TCCountrySafetyRatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class TCCountrySafetyRatingCoefficientTest {
    @InjectMocks
    private TCCountrySafetyRatingCoefficient calculator;

    @Mock
    private TCCountrySafetyRatingRepository repo;

    private AgreementDTO agreement;
    private PersonDTO person;
    @Test
    public void correctCountryTest() {
        String country = "COUNTRY";
        BigDecimal cost = BigDecimal.valueOf(10L);
        Integer rating = 2;
        BigDecimal expectedResult = BigDecimal.valueOf(5L).setScale(2);
        agreement = AgreementDTOBuilder.createAgreementDTO()
                .withCost(cost)
                .withCountry(country)
                .build();
        TCCountrySafetyRatingCoefficientDomain domain = new TCCountrySafetyRatingCoefficientDomain();
        domain.setRating(rating);
        when(repo.findByCountry(country)).thenReturn(Optional.of(domain));

        assertEquals("", expectedResult, calculator.calculatePremium(agreement, person));
    }
}
