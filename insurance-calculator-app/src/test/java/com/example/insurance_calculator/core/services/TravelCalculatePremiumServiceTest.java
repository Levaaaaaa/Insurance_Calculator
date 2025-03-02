package com.example.insurance_calculator.core.services;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.messagebroker.ProposalGeneratorQueueSender;
import com.example.insurance_calculator.services.calculate.TravelCalculatePremiumServiceImpl;
import com.example.insurance_calculator.core.underwriting.TravelPremiumCalculationResult;
import com.example.insurance_calculator.core.underwriting.TravelUnderwriting;
import com.example.insurance_calculator.core.util.AgreementSaveUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class TravelCalculatePremiumServiceTest {
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Mock
    private TravelUnderwriting underwriting;

    @Mock
    private ProposalGeneratorQueueSender mockMessageSender;

    @Mock
    private AgreementSaveUtil agreementSaveUtil;
    private AgreementDTO agreement = new AgreementDTO();


    private PersonDTO person = new PersonDTO();

    @Mock
    private RiskDTO risk;

    //correct value
    //one error
    @Test
    public void correctValueServiceTest() {
        BigDecimal riskPremium = BigDecimal.ONE;
        BigDecimal totalPremium = BigDecimal.ONE;
        List<RiskDTO> risks = List.of(risk);
        person.setSelectedRisks(risks);
        when(risk.getPremium()).thenReturn(riskPremium);
        agreement.setPersons(List.of(person));
        when(underwriting.calculatePremium(agreement, person)).thenReturn(new TravelPremiumCalculationResult(totalPremium, risks));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals("", totalPremium, result.getAgreement().getAgreementPremium());
        assertEquals("", riskPremium, result.getAgreement().getPersons().get(0).getSelectedRisks().get(0).getPremium());
    }
}
