package com.wallace.proposalapp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue createPendingProposalCreditAnalysisQueue() {
        return QueueBuilder.durable("pending-proposal.credit-analysis-service").build();
    }

    @Bean
    public Queue createPendingProposalNotificationQueue() {
        return QueueBuilder.durable("pending-approval.notification-service").build();
    }

    @Bean
    public Queue createFinishedProposalProposalQueue() {
        return QueueBuilder.durable("finished-proposal.proposal-service").build();
    }

    @Bean
    public Queue createFinishedProposalNotificationQueue() {
        return QueueBuilder.durable("finished-proposal.notification-service").build();
    }
}
