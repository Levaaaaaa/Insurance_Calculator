package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.core.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.AgreementWithRiskEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SelectedRisksEntityRepositoryTest {
    @Autowired
    private AgreementRiskEntityRepository agreementRiskEntityRepository;

    @Test
    public void isExistingRepository() {
        assertNotNull("", agreementRiskEntityRepository);
    }

    @Test
    public void correctRiskIc() {
        String riskIc = "CORRECT_RISK_IC";
        AgreementEntity AgreementEntity = new AgreementEntity();
        AgreementEntity.setId(1L);
        Optional<AgreementWithRiskEntity> domain = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, AgreementEntity);
        assertTrue("", domain.isPresent());
        assertEquals("", riskIc, domain.get().getRiskIc());
    }

    @Test
    public void incorrectRiskIc() {
        String riskIc = "WRONG_RISK_IC";
        AgreementEntity AgreementEntity = new AgreementEntity();
        AgreementEntity.setId(1L);
        Optional<AgreementWithRiskEntity> domain = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, AgreementEntity);
        assertFalse("", domain.isPresent());
    }
}
