package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.calculate.medical.CountryDefaultDayRate;
import com.example.insurance_calculator.persistence.repositories.calculate.medical.CountryDefaultDayRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static com.example.insurance_calculator.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static com.example.insurance_calculator.persistence.entities.calculate.builders.CountryDefaultDayRateBuilder.createCountryDefaultDayRate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelCalculateMedicalCountryDefaultDayCountTest {
    @Mock
    private CountryDefaultDayRateRepository cddrRepository;

    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;

    @InjectMocks
    private TravelCalculateMedicalCountryDefaultDayRate calculator;

    @Test
    public void CalculatorMedicalCddrTest() {
        init(BigDecimal.valueOf(1.1));
        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreement, person));
    }

    private void init(BigDecimal countryCoefficient) {
        String country = "COUNTRY";
        when(agreement.getCountry()).thenReturn(country);

        CountryDefaultDayRate cddrValue = mock(CountryDefaultDayRate.class);
        when(cddrValue.getDefaultDayRate()).thenReturn(countryCoefficient);
        when(cddrValue.getCountryIc()).thenReturn(country);

        when(cddrRepository.findByCountryIc(country)).thenReturn(Optional.of(cddrValue));
    }

    @Test
    public void CalculatorMedicalCddrIntegrationTest() {
        AgreementDTO agreementDTO = createAgreementDTO().withCountry("SPAIN").build();
        PersonDTO personDTO = createPersonDTO().build();
        CountryDefaultDayRate cddr = createCountryDefaultDayRate()
                .withCountryIc("SPAIN")
                .withCoefficient(BigDecimal.valueOf(1.1))
                .build();

        when(cddrRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(cddr));
        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreementDTO, personDTO));
    }
}
