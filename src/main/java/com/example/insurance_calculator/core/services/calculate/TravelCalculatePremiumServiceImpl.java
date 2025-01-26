package com.example.insurance_calculator.core.services.calculate;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.underwriting.TravelPremiumCalculationResult;
import com.example.insurance_calculator.core.underwriting.TravelUnderwriting;
import com.example.insurance_calculator.core.util.AgreementSaveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Transactional
@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService{
    @Autowired
    private TravelUnderwriting underwriting;

    @Autowired
    private AgreementSaveUtil agreementSaveUtil;


    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        return buildResult(command.getAgreement());
//        List<ValidationErrorDTO> errors = validator.validate(command.getAgreement());
//        return errors.isEmpty()
//                ? buildResult(command.getAgreement())
//                : buildResult(errors)
//                ;
    }


    private TravelCalculatePremiumCoreResult buildResult(AgreementDTO agreement) {
        TravelCalculatePremiumCoreResult result = new TravelCalculatePremiumCoreResult();
        agreement.setUuid(agreement.getUuid());

        calculatePremiumForEachRisk(agreement);

//        agreement.getPersons().forEach(person -> personEntityService.getPersonEntity(person));

        BigDecimal totalPremium = calculateTotalPremium(agreement);
        agreement.setAgreementPremium(totalPremium);
        agreementSaveUtil.saveAgreement(agreement);
        result.setAgreement(agreement);
        return result;
    }

    private void calculatePremiumForEachRisk(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult result = underwriting.calculatePremium(agreement, person);
            person.setSelectedRisks(result.getRisks());
            person.setPersonPremium(result.getTotalPremium());
        });
    }

    private BigDecimal calculateTotalPremium(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getSelectedRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private TravelCalculatePremiumCoreResult buildResult(List<ValidationErrorDTO> errors) {
        TravelCalculatePremiumCoreResult result = new TravelCalculatePremiumCoreResult();
        result.setErrors(errors);
        return result;
    }

}
