package com.example.insurance_calculator.core.underwriting.calculators.medical;


import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;

import static com.example.insurance_calculator.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static com.example.insurance_calculator.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static com.example.insurance_calculator.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TravelCalculateMedicalDayCountTest {
    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelCalculateDayCountMedical calculator;
    @Test
    public void calculatorMedicalDayCountTest() {
        init();
        assertEquals(BigDecimal.valueOf(3), calculator.calculatePremium(agreement, person));
    }
    private void init() {

        //Date dateTo = new Date();
        //Date dateFrom = new Date(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay() - dayCount);
        //dateFrom.setDate(dateFrom.getDay() - dayCount);


        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2030-05-05"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-05-02"));
        when(dateTimeUtil.getDaysBetween(agreement.getAgreementDateTo(), agreement.getAgreementDateFrom())).thenReturn(3L);

    }

    @Test
    public void calculatorMedicalDayCountIntegrationTest() {
        AgreementDTO agreementDTO = createAgreementDTO()
                .withDateFrom(createDate("2030-05-02"))
                .withDateTo(createDate("2030-05-05"))
                .build();
        when(dateTimeUtil.getDaysBetween(agreementDTO.getAgreementDateTo(), agreementDTO.getAgreementDateFrom())).thenReturn(3L);

        PersonDTO personDTO = createPersonDTO().build();
        assertEquals(BigDecimal.valueOf(3), calculator.calculatePremium(agreementDTO, personDTO));
    }
}
