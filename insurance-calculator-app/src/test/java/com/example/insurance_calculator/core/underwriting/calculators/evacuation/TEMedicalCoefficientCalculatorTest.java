package com.example.insurance_calculator.core.underwriting.calculators.evacuation;

import com.example.insurance_calculator.enums.TE_PERSON_MEDICAL_STATUS;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTOBuilder;
import com.example.insurance_calculator.persistence.entities.calculate.evacuation.TEMedicalCoefficientDomain;
import com.example.insurance_calculator.persistence.repositories.calculate.evacuation.TEMedicalCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TEMedicalCoefficientCalculatorTest {
    @InjectMocks
    private TEMedicalCoefficientCalculator calculator;

    @Mock
    private TEMedicalCoefficientRepository medCoeffRepository;

    private AgreementDTO agreementDTO;
    private PersonDTO personDTO;

    @Test
    public void perfectlyHealthyStatusTest() {
        executeAndCompare(TE_PERSON_MEDICAL_STATUS.PERFECTLY_HEALTHY, BigDecimal.ONE);
    }

    @Test
    public void slightlyIllStatusTest() {
        executeAndCompare(TE_PERSON_MEDICAL_STATUS.SLIGHTLY_ILL, BigDecimal.valueOf(2L));
    }

    @Test
    public void illStatusTest() {
        executeAndCompare(TE_PERSON_MEDICAL_STATUS.ILL, BigDecimal.valueOf(3L));
    }

    @Test
    public void hardlyIllStatusTest() {
        executeAndCompare(TE_PERSON_MEDICAL_STATUS.HARDLY_ILL, BigDecimal.valueOf(4L));
    }

/*    @Test
    public void notExistingStatusTest() {
        executeAndCompare("NOT_EXISTING_MEDICAL_STATUS", BigDecimal.ONE);
    }

 */
    private void executeAndCompare(TE_PERSON_MEDICAL_STATUS personMedicalStatus, BigDecimal expectedCoefficient) {
        expectedCoefficient = expectedCoefficient.setScale(2, RoundingMode.HALF_UP);

        personDTO = PersonDTOBuilder.createPersonDTO().withPersonMedicalStatus(personMedicalStatus).build();

        TEMedicalCoefficientDomain domain = new TEMedicalCoefficientDomain();
        domain.setCoefficient(expectedCoefficient);
        when(medCoeffRepository.findByIc(personMedicalStatus.name())).thenReturn(Optional.of(domain));

        assertEquals(expectedCoefficient, calculator.calculatePremium(agreementDTO, personDTO));
    }
}
