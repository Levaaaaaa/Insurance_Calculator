package com.example.insurance_calculator.core.services.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.domain.agreement.AgreementEntityDomain;
import com.example.insurance_calculator.core.domain.agreement.AgreementRiskEntityDomain;
import com.example.insurance_calculator.core.repositories.agreement.AgreementRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgreementRiskEntityService {
    @Autowired
    private AgreementRiskEntityRepository agreementRiskEntityRepository;

    public AgreementRiskEntityDomain saveRisk(String riskIc, AgreementEntityDomain agreementDomain) {
        Optional<AgreementRiskEntityDomain> optional = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementDomain);
        if (optional.isPresent()) {
            return optional.get();
        }

        AgreementRiskEntityDomain domain = new AgreementRiskEntityDomain();
        domain.setRiskIc(riskIc);
        domain.setAgreement(agreementDomain);
        agreementRiskEntityRepository.save(domain);
        return domain;
    }
    public List<AgreementRiskEntityDomain> saveRisks(AgreementDTO agreementDTO, AgreementEntityDomain agreementEntityDomain) {
        return agreementDTO.getSelectedRisks().stream().map(riskIc -> {
            Optional<AgreementRiskEntityDomain> optional = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementEntityDomain);
            if (optional.isEmpty()) {
                AgreementRiskEntityDomain domain = new AgreementRiskEntityDomain();
                domain.setRiskIc(riskIc);
                domain.setAgreement(agreementEntityDomain);
                agreementRiskEntityRepository.save(domain);
                return domain;
            }
            return optional.get();
        }).toList();
    }
}
