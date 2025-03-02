package com.example.insurance_calculator.services.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AgreementEntityService {
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;


    public AgreementEntity saveAgreement(AgreementDTO agreement) {
        Optional<AgreementEntity> domainOptional = agreementEntityRepository.findByDateFromAndDateToAndCountryAndPremium(
                new Date(agreement.getAgreementDateFrom().getTime()),
                new Date(agreement.getAgreementDateTo().getTime()),
                agreement.getCountry(),
                agreement.getAgreementPremium());
        if (domainOptional.isEmpty()) {
            AgreementEntity domain = new AgreementEntity();
            domain.setDateFrom(new Date(agreement.getAgreementDateFrom().getTime()));
            domain.setDateTo(new Date(agreement.getAgreementDateTo().getTime()));
            domain.setCountry(agreement.getCountry());
            domain.setPremium(agreement.getAgreementPremium());
            domain.setUuid(agreement.getUuid());
            domain.setCost(agreement.getCost());
            agreementEntityRepository.save(domain);
            return domain;
        }
        return domainOptional.get();
    }
}
