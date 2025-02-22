package com.example.insurance_calculator.core.underwriting.calculators.evacuation;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTOBuilder;
import com.example.insurance_calculator.core.entities.calculate.evacuation.TEAgeCoefficientDomain;
import com.example.insurance_calculator.core.repositories.calculate.evacuation.TEAgeCoefficientRepository;
import com.example.insurance_calculator.core.util.CalculateAgeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TEAgeCoefficientCalculatorTest {
    @InjectMocks
    private TEAgeCoefficientCalculator calculator;

    @Mock
    private TEAgeCoefficientRepository repository;

    @Mock
    private CalculateAgeUtil calculateAgeUtil;

    AgreementDTO agreementDTO;
    PersonDTO personDTO;

    @Test
    public void threeYearsOldTest() {
        executeAndCompare(3, BigDecimal.valueOf(5L));
    }

    @Test
    public void fiveteenYearsOldTest() {
        executeAndCompare(15, BigDecimal.valueOf(10L));
    }

    private void executeAndCompare(int age, BigDecimal expectedCoefficient) {
        expectedCoefficient = expectedCoefficient.setScale(2, RoundingMode.HALF_UP);
        Date birthDate = new Date();
        birthDate.setYear(birthDate.getYear() - age);
        personDTO = PersonDTOBuilder.createPersonDTO().withBirthDate(birthDate).build();

        when(calculateAgeUtil.calculateAge(personDTO.getPersonBirthDate())).thenReturn(age);
        TEAgeCoefficientDomain expectedDomain = new TEAgeCoefficientDomain();
        expectedDomain.setCoefficient(expectedCoefficient);

        when(repository.findAgeCoefficient(age)).thenReturn(Optional.of(expectedDomain));

        assertEquals(expectedCoefficient.setScale(2, RoundingMode.HALF_UP), calculator.calculatePremium(agreementDTO, personDTO));
    }
}
