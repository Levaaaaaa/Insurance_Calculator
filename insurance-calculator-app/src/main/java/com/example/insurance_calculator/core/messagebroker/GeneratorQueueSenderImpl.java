package com.example.insurance_calculator.core.messagebroker;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("docker")
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneratorQueueSenderImpl implements ProposalGeneratorQueueSender{

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void send(AgreementDTO agreementDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(agreementDTO);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
            log.info("This message was sent successfully " + message);
        }
        catch (JsonProcessingException e) {
            log.warn("Sending message into queue is broken by json processing error");
        }
    }
}
