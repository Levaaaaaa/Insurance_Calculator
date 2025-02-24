package org.example.messagebroker;

import org.example.dto.AgreementDTO;
import org.springframework.stereotype.Component;

@Component
public interface GeneratorQueueReciever {
    public AgreementDTO getAgreement();
}
