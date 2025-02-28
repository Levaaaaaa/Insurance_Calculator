package org.example.messagebroker;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {
    public static final String Q_PROPOSAL_GENERATION = "q.proposal-generation";
    public static final String Q_PROPOSAL_GENERATION_DLQ = "q.proposal-generation-dlq";
    public static final String DLQ_EXCHANGE = "dlq-exchange";
    public static final String GENERATION_ACK_EXCHANGE = "generation-ack-exchange";
    public static final String Q_PROPOSAL_GENERATION_ACK = "q.proposal-generation-ack";
    public static final String PROPOSAL_ACK_ROUTING_KEY = Q_PROPOSAL_GENERATION_ACK;
    @Bean
    public Queue proposalGenerationQueue() {
        return new Queue(Q_PROPOSAL_GENERATION);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(Q_PROPOSAL_GENERATION_DLQ);
    }

    @Bean
    public DirectExchange dlqExchange() {
        return ExchangeBuilder.directExchange(DLQ_EXCHANGE).build();
    }

    @Bean
    public Binding dlqBinding(Queue deadLetterQueue, DirectExchange dlqExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(dlqExchange).with(Q_PROPOSAL_GENERATION_DLQ);
    }

    @Bean
    public Queue proposalGenerationAckQueue() {
        return new Queue(Q_PROPOSAL_GENERATION_ACK);
    }

    @Bean
    public DirectExchange ackExchange() {
        return ExchangeBuilder.directExchange(GENERATION_ACK_EXCHANGE).build();
    }

    @Bean
    public Binding ackBinding(Queue proposalGenerationAckQueue, DirectExchange ackExchange) {
        return BindingBuilder.bind(proposalGenerationAckQueue).to(ackExchange).with(PROPOSAL_ACK_ROUTING_KEY);
    }
}
