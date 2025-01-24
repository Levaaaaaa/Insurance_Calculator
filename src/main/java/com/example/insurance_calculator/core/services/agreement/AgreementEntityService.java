package com.example.insurance_calculator.core.services.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.domain.agreement.AgreementEntityDomain;
import com.example.insurance_calculator.core.repositories.agreement.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AgreementEntityService {
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;


    public AgreementEntityDomain saveAgreement(AgreementDTO agreement) {
        Optional<AgreementEntityDomain> domainOptional = agreementEntityRepository.findBy(
                new Date(agreement.getAgreementDateFrom().getTime()),
                new Date(agreement.getAgreementDateTo().getTime()),
                agreement.getCountry(),
                agreement.getAgreementPremium());
        if (domainOptional.isEmpty()) {
            AgreementEntityDomain domain = new AgreementEntityDomain();
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