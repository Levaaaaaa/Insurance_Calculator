package org.example.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;
import org.example.services.DocGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.messagebroker.RabbitMQConfig.DLQ_NAME;
import static org.example.messagebroker.RabbitMQConfig.QUEUE_NAME;
import static org.example.util.AgreementJsonConverter.jsonToAgreement;

@Component
public class QueueListener {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Value("${rabbitmq.total.retry.count}")
    private Integer totalRetryCount;

    @Autowired
    private DocGeneratorService docGeneratorService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(Message message) throws IOException {
        try {
            processMessage(message);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            retryReceiveMessage(message);
        }
    }

    private void processMessage(Message message) throws IOException{
        String messageBody = new String(message.getBody());
        logger.info(messageBody);
        saveAgreement(jsonToAgreement(messageBody));
    }

    private void retryReceiveMessage(Message message) {
        Integer retryCount = message.getMessageProperties().getHeader("x-retry-count");
        if (retryCount == null) {
            retryCount = 0;
        }
        logger.info("Retry receive message. Retry count - " + retryCount);
        retryCount++;
        if (retryCount <= totalRetryCount) {
            message.getMessageProperties().setHeader("x-retry-count", retryCount);
            rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        }
        else {
            rabbitTemplate.convertAndSend(DLQ_NAME, message);
        }
    }
    private void saveAgreement(AgreementDTO agreementDTO) throws IOException{
        docGeneratorService.saveDocumentIntoFile(agreementDTO);
    }

}
