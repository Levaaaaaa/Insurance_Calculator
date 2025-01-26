package com.example.insurance_calculator.core.services.agreement;

import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.core.entities.agreement.PersonInAgreementEntity;
import com.example.insurance_calculator.core.entities.agreement.PersonsRiskEntity;
import com.example.insurance_calculator.core.repositories.agreement.PersonRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonRiskEntityService {
    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;

    public PersonsRiskEntity savePersonRisk(RiskDTO risk, PersonInAgreementEntity personEntityDomain) {
        Optional<PersonsRiskEntity> optional = personRiskEntityRepository.findByIcAndPerson(risk.getRiskIc(), personEntityDomain);
        if (optional.isPresent()) {
            return optional.get();
        }

        PersonsRiskEntity riskDomain = new PersonsRiskEntity();
        riskDomain.setRiskIc(risk.getRiskIc());
        riskDomain.setPerson(personEntityDomain);
        riskDomain.setPremium(risk.getPremium());
        personRiskEntityRepository.save(riskDomain);

        return riskDomain;
    }
/*
    public List<PersonRiskEntityDomain> savePersonRisk(PersonDTO person, AgreementPersonEntityDomain personDomain) {
        return person.getSelectedRisks().stream().map(risk -> {

            Optional<PersonRiskEntityDomain> optional = personRiskEntityRepository.findByIcAndPerson(risk.getRiskIc(), personDomain);
            if (optional.isPresent()) {
                return optional.get();
            }

            PersonRiskEntityDomain riskDomain = new PersonRiskEntityDomain();
            riskDomain.setRiskIc(risk.getRiskIc());
            riskDomain.setPerson(personDomain);
            riskDomain.setPremium(risk.getPremium());
            personRiskEntityRepository.save(riskDomain);

            return riskDomain;
        }).toList();
    }

 */
}
