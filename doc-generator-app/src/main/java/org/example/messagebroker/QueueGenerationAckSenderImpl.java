package org.example.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dto.AgreementDTO;
import org.example.dto.GenerationAckDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

import static org.example.messagebroker.RabbitMQConfig.GENERATION_ACK_EXCHANGE;
import static org.example.messagebroker.RabbitMQConfig.PROPOSAL_ACK_ROUTING_KEY;
import static org.example.util.DTOJsonConverter.jsonFromAck;

@Component
public class QueueGenerationAckSenderImpl implements QueueProposalGenerationAckSender{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(QueueGenerationAckSenderImpl.class);

    @Override
    public void send(AgreementDTO agreementDTO, Path pathToSave) {
        GenerationAckDTO message = new GenerationAckDTO();
        message.setAgreementUUID(agreementDTO.getUuid());
        message.setPath(pathToSave);
        logger.info("Generating message: uuid - {}, path - {}", message.getAgreementUUID().toString(), pathToSave.toString());
        try {
            rabbitTemplate.convertAndSend(GENERATION_ACK_EXCHANGE, PROPOSAL_ACK_ROUTING_KEY, jsonFromAck(message));
            logger.info("Message was sent into exchange + " + GENERATION_ACK_EXCHANGE);
        }
        catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
