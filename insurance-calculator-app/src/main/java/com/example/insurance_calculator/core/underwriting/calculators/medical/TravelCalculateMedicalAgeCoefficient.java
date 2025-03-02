package com.example.insurance_calculator.core.underwriting.calculators.medical;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.repositories.calculate.medical.TMAgeCoefficientRepository;
import com.example.insurance_calculator.core.util.CalculateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

import static com.example.insurance_calculator.core.util.CheckApplicationPropertiesUtil.checkProperty;


@Component
class TravelCalculateMedicalAgeCoefficient implements TravelRiskPremiumCalculatorMedicalComponent{
    @Autowired
    private TMAgeCoefficientRepository acRepository;

/*
    @Autowired
    private DateTimeUtil dateTimeUtil;

 */

    @Autowired
    private CalculateAgeUtil calculateAgeUtil;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        try {
            return checkProperty("medical.risk.age.coefficient.enabled")
                    ? acRepository
                        .findCoefficient(
                                calculateAgeUtil.calculateAge(
                                        person.getPersonBirthDate()
                                )
                        )
                        .get()
                        .getCoefficient()
                    : BigDecimal.ONE;
        }
        catch (IOException e) {
            return BigDecimal.ONE;
        }
    }

    public BigDecimal calculatePremium(PersonDTO person) {
        return calculatePremium(null, person);
    }
    /*
    private int calculateAge(PersonDTO person) {
        LocalDate personBirthDate = toLocalDate(person.getPersonBirthDate());
        LocalDate now = toLocalDate(dateTimeUtil.getCurrentDateTime());
        int res = Period.between(personBirthDate, now).getYears();
        return res;
    }
    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

     */
}
