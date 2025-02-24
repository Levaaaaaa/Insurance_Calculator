package org.example.messagebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.messagebroker.RabbitMQConfig.QUEUE_NAME;

@Component
public class QueueListener {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String message) throws IOException {
        logger.info(message);
    }
}
