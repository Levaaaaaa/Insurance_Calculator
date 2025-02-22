package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.core.entities.agreement.PersonInAgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.PersonsRiskEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRiskEntityRepositoryTest {
    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;

    @Test
    public void isRepositoryExists() {
        assertNotNull("", personRiskEntityRepository);
    }

    @Test
    public void correctIcAndPerson() {
        String ic = "CORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        PersonInAgreementEntity domain = new PersonInAgreementEntity();
        domain.setId(1L);
        Optional<PersonsRiskEntity> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertTrue("", risk.isPresent());
        assertEquals("", ic, risk.get().getRiskIc());
        assertEquals("", premium, risk.get().getPremium());
        assertEquals("", 1L, risk.get().getPerson().getId());
    }

    @Test
    public void incorrectIcTest() {
        String ic = "INCORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        PersonInAgreementEntity domain = new PersonInAgreementEntity();
        domain.setId(1L);
        Optional<PersonsRiskEntity> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertFalse("", risk.isPresent());
    }

    @Test
    public void incorrectPersonTest() {
        String ic = "CORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        PersonInAgreementEntity domain = new PersonInAgreementEntity();
        domain.setId(0L);
        Optional<PersonsRiskEntity> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertFalse("", risk.isPresent());
    }
}
