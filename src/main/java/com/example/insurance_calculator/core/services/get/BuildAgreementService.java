package com.example.insurance_calculator.core.services.get;

import com.example.insurance_calculator.core.RISKS;
import com.example.insurance_calculator.core.TE_PERSON_MEDICAL_STATUS;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.core.domain.agreement.*;
import com.example.insurance_calculator.core.repositories.agreement.AgreementPersonEntityRepository;
import com.example.insurance_calculator.core.repositories.agreement.AgreementRiskEntityRepository;
import com.example.insurance_calculator.core.repositories.agreement.PersonRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BuildAgreementService {


    @Autowired
    private AgreementRiskEntityRepository riskEntityRepository;


    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;

    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;
    public AgreementDTO buildAgreement(AgreementEntityDomain agreementEntityDomain){
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementEntityDomain.getDateFrom());
        agreementDTO.setAgreementDateTo(agreementEntityDomain.getDateTo());
        agreementDTO.setCountry(agreementEntityDomain.getCountry());
        agreementDTO.setUuid(agreementEntityDomain.getUuid());
        agreementDTO.setSelectedRisks(
                buildSelectedRisks(agreementEntityDomain)
        );
        agreementDTO.setAgreementPremium(agreementEntityDomain.getPremium());

        agreementDTO.setPersons(
                buildPersons(agreementEntityDomain)
        );
        agreementDTO.setCost(agreementEntityDomain.getCost());
        return agreementDTO;
    }

    private List<String> buildSelectedRisks(AgreementEntityDomain agreementEntityDomain) {
        List<String> risks = Arrays.stream(RISKS.values())
                .map(risk ->
                {
                    return riskEntityRepository.findByIcAndAgreement(risk.name(), agreementEntityDomain);
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(AgreementRiskEntityDomain::getRiskIc)
                .toList();
        return risks;
    }

    private List<PersonDTO> buildPersons(AgreementEntityDomain agreementEntityDomain) {
        return agreementPersonEntityRepository.findByAgreement(agreementEntityDomain).stream()
                .map(agreementPerson -> {
                    PersonDTODomain personDomain = agreementPerson.getPerson();
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonFirstName(personDomain.getPersonFirstName());
                    personDTO.setPersonLastName(personDomain.getPersonLastName());
                    personDTO.setPersonBirthDate(personDomain.getPersonBirthDate());
                    personDTO.setMedicalRiskLimitLevel(agreementPerson.getMedicalRiskLimitLevel());
                    personDTO.setPersonIc(personDomain.getPersonIc());
                    personDTO.setPersonPremium(agreementPerson.getPremium());
                    personDTO.setSelectedRisks(buildPersonRisks(agreementPerson));
                    try {
                        personDTO.setPersonMedicalStatus(TE_PERSON_MEDICAL_STATUS.valueOf(agreementPerson.getPersonMedicalStatus()));
                    }
                    catch (IllegalArgumentException e) {
                        //impossible, because user can't input invalid medical status in agreementGetController
                        e.printStackTrace();
                    }
                    return personDTO;
                }).toList();
    }

    private List<RiskDTO> buildPersonRisks(AgreementPersonEntityDomain agreementPerson) {
        return Arrays.stream(RISKS.values())
                .map(riskIc ->
                {
                    return personRiskEntityRepository.findByIcAndPerson(
                        riskIc.name(),
                        agreementPerson
                    );
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(riskDomain -> {
                    RiskDTO risk = new RiskDTO();
                    risk.setRiskIc(riskDomain.getRiskIc());
                    risk.setPremium(riskDomain.getPremium());
                    return risk;
                })
                .toList();
    }
}
