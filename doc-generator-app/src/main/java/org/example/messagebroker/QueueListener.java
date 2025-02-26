package org.example.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;
import org.example.services.DocGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.messagebroker.RabbitMQConfig.QUEUE_NAME;

@Component
public class QueueListener {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private DocGeneratorService docGeneratorService;

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String message) throws IOException {
        logger.info(message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            docGeneratorService.generateDocument(objectMapper.readValue(message, AgreementDTO.class));
        }
        catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
