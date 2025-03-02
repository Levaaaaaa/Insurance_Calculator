package com.example.insurance_calculator.core.util;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.persistence.entities.agreement.*;
import com.example.insurance_calculator.services.agreement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/*
    risk service saves it's agreement if it hasn't saved
    agreement service saves each its person
    person saves only itself
 */
@Component
public class AgreementSaveUtil {
    @Autowired
    private AgreementEntityService agreementEntityService;

    @Autowired
    private PersonEntityService personEntityService;

    @Autowired
    private AgreementRiskEntityService riskEntityService;

    @Autowired
    private AgreementPersonEntityService agreementPersonEntityService;
    @Autowired
    private PersonRiskEntityService personRiskEntityService;
    public void saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = agreementEntityService.saveAgreement(agreementDTO);

//        List<PersonDTODomain> personDTOs = agreementDTO.getPersons().stream().map(person-> personEntityService.getPersonEntity(person, agreementEntityDomain)).toList();
        List<PersonsRiskEntity> personRisks = new LinkedList<>();


        List<PersonInAgreementEntity> personDomains =
                agreementDTO.getPersons().stream().map(
                        person -> {
                            PersonEntity personDomain = personEntityService.getPersonEntity(person, agreementEntity);
                            PersonInAgreementEntity personEntityDomain =
                                    agreementPersonEntityService.savePerson(
                                            personDomain, person, agreementEntity
                                    );
                            personRisks.addAll(
                                person.getSelectedRisks().stream().map(
                                        risk -> {
                                            return personRiskEntityService.savePersonRisk(risk, personEntityDomain);
                                        })
                                        .toList()
                            );
                       return personEntityDomain;
                        })
                        .toList();

        List<AgreementWithRiskEntity> agreementRisks = agreementDTO.getSelectedRisks().stream().map(riskIc -> {
            return riskEntityService.saveRisk(riskIc, agreementEntity);
        }).toList();

//        List<AgreementPersonEntityDomain> persons = agreementPersonEntityService.savePersons(agreementDTO);
        //riskEntityService.saveRisks(agreementDTO, agreementEntityDomain);

    }
}
