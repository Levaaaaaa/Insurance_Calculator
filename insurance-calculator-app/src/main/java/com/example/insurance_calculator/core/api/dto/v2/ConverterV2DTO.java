package com.example.insurance_calculator.core.api.dto.v2;


import com.example.insurance_calculator.enums.TE_PERSON_MEDICAL_STATUS;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.core.util.GenerateAgreementUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.example.insurance_calculator.core.util.GeneratePersonIc.generatePersonIcs;

@Component
public class ConverterV2DTO {

    @Autowired
    private GenerateAgreementUUID generateAgreementUUID;

    public TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV2 request) throws IllegalArgumentException{
        return new TravelCalculatePremiumCoreCommand(buildAgreement(prepareRequest(request)));
    }


    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ? buildResponseWithErrors(result) : buildSuccessfulResponse(result);
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        AgreementDTO agreement = result.getAgreement();

        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setSelectedRisks(agreement.getSelectedRisks());
        response.setPersons(agreement.getPersons().stream().map(this::buildPersonResponse).toList());
        response.setUuid(agreement.getUuid());
        response.setCost(agreement.getCost());

        return response;
    }

    private PersonResponseV2DTO buildPersonResponse(PersonDTO personDTO) {
        PersonResponseV2DTO response = new PersonResponseV2DTO();
        response.setPersonIc(personDTO.getPersonIc());
        response.setPersonFirstName(personDTO.getPersonFirstName());
        response.setPersonLastName(personDTO.getPersonLastName());
        response.setPersonBirthDate(personDTO.getPersonBirthDate());
        response.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        response.setSelectedRisks(personDTO.getSelectedRisks());
        response.setPersonPremium(personDTO.getPersonPremium());
        if (personDTO.getPersonMedicalStatus() != null) {
            response.setPersonMedicalStatus(personDTO.getPersonMedicalStatus().name());
        }
        return response;
    }
    private TravelCalculatePremiumResponseV2 buildResponseWithErrors(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(result.getErrors());
        return response;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) throws IllegalArgumentException{
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setUuid(generateAgreementUUID.generate(agreement));
        if (request.getPersons() == null || request.getPersons().isEmpty()) {
            agreement.setPersons(List.of());
        } else{
                agreement.setPersons(request.getPersons().stream().map(this::buildPerson).toList());
            }
//        agreement.setPersons(generatePersonIcs(agreement));

        agreement.setCost(request.getCost());
        return agreement;
    }

    private TravelCalculatePremiumRequestV2 prepareRequest(TravelCalculatePremiumRequestV2 request) {
        if (request.getPersons() == null || request.getPersons().isEmpty()) {
            return request;
        }

        request.getPersons().forEach(p -> p.setSelectedRisks(request.getSelectedRisks()));

        /*
        for (int i = 0; i < request.getPersons().size(); i++) {
            request.getPersons().get(i).setPersonIc("PERSON_#"+new Date().getTime());
        }
*/

        return request;
    }
    private PersonDTO buildPerson(PersonRequestV2DTO requestPerson) {
        PersonDTO person = new PersonDTO();
//        person.setPersonIc(requestPerson.getPersonIc());
        if (requestPerson.getSelectedRisks() != null && !requestPerson.getSelectedRisks().isEmpty()) {
            person.setSelectedRisks(requestPerson.getSelectedRisks()
                .stream().map(this::buildRisk)
                .toList()
        );
        }
        else {
            person.setSelectedRisks(List.of());
        }

        person.setPersonFirstName(requestPerson.getPersonFirstName());
        person.setPersonLastName(requestPerson.getPersonLastName());
        person.setMedicalRiskLimitLevel(requestPerson.getMedicalRiskLimitLevel());
        person.setPersonBirthDate(requestPerson.getPersonBirthDate());
        person.setPersonIc(UUID.fromString(requestPerson.getPersonIc()));
        if (requestPerson.getPersonMedicalStatus() == null) {
            person.setPersonMedicalStatus(null);
        }
        else {
            try {
                person.setPersonMedicalStatus(TE_PERSON_MEDICAL_STATUS.valueOf(requestPerson.getPersonMedicalStatus()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unsupported value of field personMedicalStatus!");
            }
        }
        return person;
    }

    private RiskDTO buildRisk(String riskIc) {
        return new RiskDTO(riskIc, BigDecimal.ZERO);
    }


}
