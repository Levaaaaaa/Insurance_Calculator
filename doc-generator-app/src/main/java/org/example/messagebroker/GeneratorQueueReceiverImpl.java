package org.example.messagebroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.example.dto.AgreementDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.RabbitExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

import static org.example.messagebroker.RabbitMQConfig.QUEUE_NAME;

@Log4j2
@Component
public class GeneratorQueueReceiverImpl implements GeneratorQueueReciever{

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Override
    public AgreementDTO getAgreement() {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] messageBody = rabbitTemplate.receive(QUEUE_NAME).getBody();
        log.info("Message " + messageBody + " was received in doc-generator-app from queue " + QUEUE_NAME);
        try {
            AgreementDTO agreementDTO = objectMapper.readValue(messageBody, AgreementDTO.class);
            log.info("Received message was converted into agreementDTO successfully");
            return agreementDTO;
        } catch (IOException e) {
            log.warn("Error converting message body into AgreementDTO");
        }
        throw new MessageConversionException("Error converting message " + Arrays.toString(messageBody) + "body into AgreementDTO. Queue name - " + QUEUE_NAME);
    }
}
