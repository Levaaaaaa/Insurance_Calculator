package com.example.insurance_calculator.services.get;

import com.example.insurance_calculator.enums.RISKS;
import com.example.insurance_calculator.enums.TE_PERSON_MEDICAL_STATUS;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementWithRiskEntity;
import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import com.example.insurance_calculator.persistence.entities.agreement.PersonInAgreementEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.AgreementPersonEntityRepository;
import com.example.insurance_calculator.persistence.repositories.agreement.AgreementRiskEntityRepository;
import com.example.insurance_calculator.persistence.repositories.agreement.PersonRiskEntityRepository;
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
    public AgreementDTO buildAgreement(AgreementEntity agreementEntity){
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementEntity.getDateFrom());
        agreementDTO.setAgreementDateTo(agreementEntity.getDateTo());
        agreementDTO.setCountry(agreementEntity.getCountry());
        agreementDTO.setUuid(agreementEntity.getUuid());
        agreementDTO.setSelectedRisks(
                buildSelectedRisks(agreementEntity)
        );
        agreementDTO.setAgreementPremium(agreementEntity.getPremium());

        agreementDTO.setPersons(
                buildPersons(agreementEntity)
        );
        agreementDTO.setCost(agreementEntity.getCost());
        return agreementDTO;
    }

    private List<String> buildSelectedRisks(AgreementEntity agreementEntity) {
        List<String> risks = Arrays.stream(RISKS.values())
                .map(risk ->
                {
                    return riskEntityRepository.findByIcAndAgreement(risk.name(), agreementEntity);
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(AgreementWithRiskEntity::getRiskIc)
                .toList();
        return risks;
    }

    private List<PersonDTO> buildPersons(AgreementEntity agreementEntity) {
        return agreementPersonEntityRepository.findByAgreement(agreementEntity).stream()
                .map(agreementPerson -> {
                    PersonEntity personDomain = agreementPerson.getPerson();
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

    private List<RiskDTO> buildPersonRisks(PersonInAgreementEntity agreementPerson) {
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
