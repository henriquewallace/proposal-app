package com.wallace.proposalapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.pendingproposal.exchange}")
    private String pendingProposalExchange;

    @Value("${rabbitmq.finishedproposal.exchange}")
    private String finisahedProposalExchange;

    @Value("${rabbitmq.pendingproposal.dlexchange}")
    private String pendingProposalDLX;


    @Bean
    public Queue createPendingProposalCreditAnalysisQueue() {
        return QueueBuilder.durable("pending-proposal.credit-analysis-service")
                .deadLetterExchange(pendingProposalDLX)
                .build();
    }

    @Bean
    public Queue createPendingProposalNotificationQueue() {
        return QueueBuilder.durable("pending-proposal.notification-service").build();
    }

    @Bean
    public Queue createFinishedProposalProposalQueue() {
        return QueueBuilder.durable("finished-proposal.proposal-service").build();
    }

    @Bean
    public Queue createFinishedProposalNotificationQueue() {
        return QueueBuilder.durable("finished-proposal.notification-service").build();
    }

    @Bean
    public Queue createPendingProposalDLQueue() {
        return QueueBuilder.durable("pending-proposal.dlq").build();
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializerAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createPendingProposalFanoutExchange() {
        return ExchangeBuilder.fanoutExchange(pendingProposalExchange).build();
    }
    @Bean
    public FanoutExchange createFinishedProposalFanoutExchange() {
        return ExchangeBuilder.fanoutExchange(finisahedProposalExchange).build();
    }

    @Bean
    public FanoutExchange createPendingProposalDLExchange() {
        return ExchangeBuilder.fanoutExchange(pendingProposalDLX).build();
    }

    @Bean
    public Binding createPendingProposalDLQBinding() {
        return BindingBuilder.bind(createPendingProposalDLQueue())
                .to(createPendingProposalDLExchange());
    }

    @Bean
    public Binding createPendingProposalCreditAnalysisBinding() {
        return BindingBuilder.bind(createPendingProposalCreditAnalysisQueue())
                .to(createPendingProposalFanoutExchange());
    }

    @Bean
    public Binding createPendingProposalNotificationBinding() {
        return BindingBuilder.bind(createPendingProposalNotificationQueue())
                .to(createPendingProposalFanoutExchange());
    }

    @Bean
    public Binding createFinishedProposalProposalBinding() {
        return BindingBuilder.bind(createFinishedProposalProposalQueue())
                .to(createFinishedProposalFanoutExchange());
    }

    @Bean
    public Binding createFinishedProposalNotificationBinding() {
        return BindingBuilder.bind(createFinishedProposalNotificationQueue())
                .to(createFinishedProposalFanoutExchange());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}
