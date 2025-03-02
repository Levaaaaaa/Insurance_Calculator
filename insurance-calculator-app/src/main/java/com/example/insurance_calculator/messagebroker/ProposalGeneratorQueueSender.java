package com.example.insurance_calculator.messagebroker;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import org.springframework.stereotype.Component;

@Component

public interface ProposalGeneratorQueueSender {
    public void send(AgreementDTO agreementDTO);
}
