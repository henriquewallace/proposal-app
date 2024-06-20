package com.wallace.proposalapp.listener;

import com.wallace.proposalapp.converter.ProposalConverter;
import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import com.wallace.proposalapp.repository.ProposalRepository;
import com.wallace.proposalapp.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FinishedProposalListener {

    private final ProposalRepository proposalRepository;
    private final WebSocketService webSocketService;
    private final ProposalConverter proposalConverter;

    @RabbitListener(queues = "${rabbitmq.queue.finished.proposal}")
    public void finishedProposal(@Payload Proposal proposal) {
        proposalRepository.save(proposal);

        ProposalResponseDTO proposalResponseDTO = proposalConverter.from(proposal);
        webSocketService.notify(proposalResponseDTO);
    }
}
