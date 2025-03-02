package com.example.insurance_calculator.core.repositories.agreement;

import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.AgreementEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AgreementEntityRepositoryTest {
    /*
    11.04.2024
    дописать тест, тесты на AgreementRisksEntityRepository,
    обновить sql скрипт
    сделать сервис AgreementRiskEntity
     */
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;

    @Test
    public void isRepositoryExisting() {
        assertNotNull("",agreementEntityRepository);
    }

    @Test
    public void allCorrectTest() {
        Date dateFrom = Date.valueOf("2050-02-02");
        Date dateTo = Date.valueOf("2050-02-03");
        String country = "SPAIN";
        BigDecimal premium = BigDecimal.ONE.setScale(2);
        Optional<AgreementEntity> domain = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                dateFrom,
                dateTo,
                country,
                premium
                );
        assertTrue("", domain.isPresent());
        assertEquals("", dateFrom, domain.get().getDateFrom());
        assertEquals("", dateTo, domain.get().getDateTo());
        assertEquals("", country, domain.get().getCountry());
        assertEquals("", premium, domain.get().getPremium());
    }

    @Test
    public void incorrectDateFrom() {
        Date dateFrom = Date.valueOf("2049-02-02");
        Date dateTo = Date.valueOf("2050-02-03");
        String country = "SPAIN";
        BigDecimal premium = BigDecimal.ONE.setScale(2);
        Optional<AgreementEntity> domain = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                dateFrom,
                dateTo,
                country,
                premium
        );
        assertFalse("", domain.isPresent());
    }

    @Test
    public void incorrectDateTo() {
        Date dateFrom = Date.valueOf("2050-02-02");
        Date dateTo = Date.valueOf("2049-02-03");
        String country = "SPAIN";
        BigDecimal premium = BigDecimal.ONE.setScale(2);
        Optional<AgreementEntity> domain = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                dateFrom,
                dateTo,
                country,
                premium
        );
        assertFalse("", domain.isPresent());
    }
    @Test
    public void incorrectCountry() {
        Date dateFrom = Date.valueOf("2050-02-02");
        Date dateTo = Date.valueOf("2050-02-03");
        String country = "INCORRECT_COUNTRY";
        BigDecimal premium = BigDecimal.ONE.setScale(2);
        Optional<AgreementEntity> domain = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                dateFrom,
                dateTo,
                country,
                premium
        );
        assertFalse("", domain.isPresent());
    }

    @Test
    public void incorrectPremium() {
        Date dateFrom = Date.valueOf("2050-02-02");
        Date dateTo = Date.valueOf("2050-02-03");
        String country = "SPAIN";
        BigDecimal premium = BigDecimal.ZERO.setScale(2);
        Optional<AgreementEntity> domain = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                dateFrom,
                dateTo,
                country,
                premium
        );
        assertFalse("", domain.isPresent());
    }
}
