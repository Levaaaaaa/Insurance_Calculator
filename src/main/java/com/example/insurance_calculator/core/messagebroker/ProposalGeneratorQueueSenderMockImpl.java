package com.example.insurance_calculator.core.messagebroker;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"docker", "local" ,"test"})
@Component
public class ProposalGeneratorQueueSenderMockImpl implements ProposalGeneratorQueueSender{
    private static final Logger logger = LoggerFactory.getLogger(ProposalGeneratorQueueSenderMockImpl.class);

    @Override
    public void send(AgreementDTO agreementDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResult;
        try {
            String jsonResult = objectMapper.writeValueAsString(agreementDTO);
            logger.info("Mock Generator Queue Sender log. Agreement - " + jsonResult);
        }
        catch (JsonProcessingException e) {
            logger.warn("Json processing error - " + e.getMessage());
        }
    }
}
