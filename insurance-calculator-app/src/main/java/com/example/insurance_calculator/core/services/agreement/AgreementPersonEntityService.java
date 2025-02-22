package com.example.insurance_calculator.core.services.agreement;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.PersonInAgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.PersonEntity;
import com.example.insurance_calculator.core.repositories.agreement.AgreementPersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgreementPersonEntityService {
    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;


    public PersonInAgreementEntity savePerson(PersonEntity personDomain, PersonDTO person, AgreementEntity agreementDomain) {
        Optional<PersonInAgreementEntity> optional = agreementPersonEntityRepository.findByName(
                person.getPersonFirstName(),
                person.getPersonLastName(),
                person.getPersonIc()
        );

        if (optional.isPresent()) {
            return optional.get();
        }

        PersonInAgreementEntity newDomain = new PersonInAgreementEntity();

        newDomain.setPerson(personDomain);
        newDomain.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());
        newDomain.setPremium(person.getPersonPremium());
        newDomain.setAgreement(agreementDomain);
        if (person.getPersonMedicalStatus() != null) {
            newDomain.setPersonMedicalStatus(person.getPersonMedicalStatus().name());
        }
        agreementPersonEntityRepository.save(newDomain);

        return newDomain;
    }
    /*
    public List<AgreementPersonEntityDomain> savePersons(AgreementDTO agreementDTO) {
        AgreementEntityDomain agreementDomain = agreementEntityService.saveAgreement(agreementDTO);
        return agreementDTO.getPersons().stream().map(person -> {
            Optional<AgreementPersonEntityDomain> domain = agreementPersonEntityRepository.findByName(
                    person.getPersonFirstName(),
                    person.getPersonLastName(),
                    person.getPersonIc());
            if (domain.isPresent()) {
                return domain.get();
            }
            AgreementPersonEntityDomain newDomain = new AgreementPersonEntityDomain();
            newDomain.setFirstName(person.getPersonFirstName());
            newDomain.setLastName(person.getPersonLastName());
            newDomain.setPersonIc(person.getPersonIc());
            newDomain.setBirthDate(new Date(person.getPersonBirthDate().getTime()));
            newDomain.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());
            newDomain.setPremium(person.getPersonPremium());
            newDomain.setAgreement(agreementDomain);

            agreementPersonEntityRepository.save(newDomain);
            return newDomain;
        }).toList();
    }

     */
}
