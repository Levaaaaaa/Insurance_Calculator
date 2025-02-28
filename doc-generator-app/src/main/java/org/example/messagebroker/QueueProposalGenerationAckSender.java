package org.example.messagebroker;

import org.example.dto.AgreementDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public interface QueueProposalGenerationAckSender {
    public void send(AgreementDTO agreementDTO, Path pathToSave);
}
