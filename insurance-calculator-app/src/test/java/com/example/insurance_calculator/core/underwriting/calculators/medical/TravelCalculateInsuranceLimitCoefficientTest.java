package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.calculate.medical.MedicalRiskLimitLevel;
import com.example.insurance_calculator.persistence.repositories.calculate.medical.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static com.example.insurance_calculator.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static com.example.insurance_calculator.persistence.entities.calculate.builders.MedicalRiskLimitLevelBuilder.createMedicalRiskLimitLevel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TravelCalculateInsuranceLimitCoefficientTest {
    @Mock
    private MedicalRiskLimitLevelRepository mrllRepository;
    @InjectMocks
    private TravelCalculateInsuranceLimitCoefficientMedical calculator;

    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;

    @Test
    public void TravelCalculateInsuranceLimitCoefficientCorrectValueTest() {
        init("LEVEL_10000", 1.0);
        assertEquals(BigDecimal.valueOf(1.0), calculator.calculatePremium(agreement, person));
    }

    private void init(String requestLimitLevelValue, Double expectedValue) {
        when(person.getMedicalRiskLimitLevel()).thenReturn(requestLimitLevelValue);

        MedicalRiskLimitLevel mrll = mock(MedicalRiskLimitLevel.class);
        when(mrll.getMedicalRiskLimitLevelIc()).thenReturn(requestLimitLevelValue);
        when(mrll.getCoefficient()).thenReturn(BigDecimal.valueOf(expectedValue));

        when(mrllRepository.findByMedicalRiskLimitLevelIc(requestLimitLevelValue)).thenReturn(Optional.of(mrll));

//        ReflectionTestUtils.setField(calculator, "mrllRepository", mrllRepository);
    }
    @Test
    public void TravelCalculateInsuranceLimitCoefficientCorrectValueIntegrationTest() {
        PersonDTO p = createPersonDTO().withMedicalRiskLimitLevel("LEVEL_10000").build();
        AgreementDTO a = createAgreementDTO().build();
        MedicalRiskLimitLevel mrll = createMedicalRiskLimitLevel()
                .withIc(p.getMedicalRiskLimitLevel())
                .withCoefficient(BigDecimal.valueOf(1.0))
                .build();
        when(mrllRepository.findByMedicalRiskLimitLevelIc(p.getMedicalRiskLimitLevel())).thenReturn(Optional.of(mrll));

    }
}
