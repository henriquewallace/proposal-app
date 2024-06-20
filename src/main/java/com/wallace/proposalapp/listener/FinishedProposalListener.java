package com.wallace.proposalapp.listener;

import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FinishedProposalListener {

    private final ProposalRepository proposalRepository;

    @RabbitListener(queues = "${rabbitmq.queue.finished.proposal}")
    public void finishedProposal(@Payload Proposal proposal) {
        proposalRepository.save(proposal);
    }
}
