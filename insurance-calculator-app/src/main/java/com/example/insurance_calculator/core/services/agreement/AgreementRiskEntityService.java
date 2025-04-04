package com.example.insurance_calculator.core.services.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementWithRiskEntity;
import com.example.insurance_calculator.persistence.repositories.agreement.AgreementRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgreementRiskEntityService {
    @Autowired
    private AgreementRiskEntityRepository agreementRiskEntityRepository;

    public AgreementWithRiskEntity saveRisk(String riskIc, AgreementEntity agreementDomain) {
        Optional<AgreementWithRiskEntity> optional = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementDomain);
        if (optional.isPresent()) {
            return optional.get();
        }

        AgreementWithRiskEntity domain = new AgreementWithRiskEntity();
        domain.setRiskIc(riskIc);
        domain.setAgreement(agreementDomain);
        agreementRiskEntityRepository.save(domain);
        return domain;
    }
    public List<AgreementWithRiskEntity> saveRisks(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        return agreementDTO.getSelectedRisks().stream().map(riskIc -> {
            Optional<AgreementWithRiskEntity> optional = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementEntity);
            if (optional.isEmpty()) {
                AgreementWithRiskEntity domain = new AgreementWithRiskEntity();
                domain.setRiskIc(riskIc);
                domain.setAgreement(agreementEntity);
                agreementRiskEntityRepository.save(domain);
                return domain;
            }
            return optional.get();
        }).toList();
    }
}
