package com.example.insurance_calculator.messagebroker.ackreceiver;

import com.example.insurance_calculator.core.api.dto.GeneratedAckDTO;
import com.example.insurance_calculator.services.ack.SaveDocGeneratorAckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.insurance_calculator.messagebroker.RabbitMQConfig.Q_PROPOSAL_GENERATION_ACK;

@Component
public class ProposalAckListener {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SaveDocGeneratorAckService saveService;

    private static final Logger logger = LoggerFactory.getLogger(ProposalAckListener.class);

    @RabbitListener(queues = Q_PROPOSAL_GENERATION_ACK)
    public void receiveAck(Message message) {
        String messageBody = new String(message.getBody());
        try {
            GeneratedAckDTO ackDTO = objectMapper.readValue(messageBody, GeneratedAckDTO.class);
            logger.info("ack uuid - " + ackDTO.getAgreementUUID() + "/ ack path + " + ackDTO.getPath());
            //todo ack service, db table schema, entity, repo
            saveService.saveAck(ackDTO);
            logger.info("Document generating error ack was saved successfully!");
        } catch (IOException e) {
            logger.error("Exception with message - " + messageBody + "Exception - " + e.getMessage());

        }
    }
}
