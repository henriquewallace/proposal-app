package com.wallace.proposalapp.service;

import com.wallace.proposalapp.converter.ProposalConverter;
import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.dto.ProposalRequestDTO;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import com.wallace.proposalapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProposalConverter proposalConverter;
    private final NotificationService notificationService;

    private final String exchange;

    public ProposalService(ProposalRepository proposalRepository,
                           ProposalConverter proposalConverter,
                           NotificationService notificationService,
                           @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.proposalConverter = proposalConverter;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public ProposalResponseDTO createProposal(ProposalRequestDTO requestDTO) {
        Proposal proposal = proposalConverter.from(requestDTO);
        proposalRepository.save(proposal);

        notifyRabbitMQ(proposal);

        return proposalConverter.from(proposal);
    }

    public List<ProposalResponseDTO> getAllProposals() {
        List<ProposalResponseDTO> proposalsDTOList = new ArrayList<>();

        proposalRepository.findAll().forEach(proposal -> {
            var proposalDTO = proposalConverter.from(proposal);

            proposalsDTOList.add(proposalDTO);
        });

        return proposalsDTOList;
    }

    private void notifyRabbitMQ(Proposal proposal) {
        try {
            notificationService.notify(proposal, exchange);
        } catch (RuntimeException runtimeException) {
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);
        }
    }
}
