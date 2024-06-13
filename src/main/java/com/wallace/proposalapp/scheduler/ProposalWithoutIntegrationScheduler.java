package com.wallace.proposalapp.scheduler;

import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.repository.ProposalRepository;
import com.wallace.proposalapp.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProposalWithoutIntegrationScheduler {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;

    private final String exchange;

    private final Logger logger = LoggerFactory.getLogger(ProposalWithoutIntegrationScheduler.class);

    public ProposalWithoutIntegrationScheduler(ProposalRepository proposalRepository,
                                               NotificationService notificationService,
                                               @Value("${rabbitmq.pendingproposal.exchange}")  String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10000)
    public void findNotIntegratedProposals() {
        proposalRepository.getAllProposalsByIntegrated().forEach(proposal -> {
            try {
                notificationService.notify(proposal, exchange);
                updateProposal(proposal);
            } catch (RuntimeException ex) {
                logger.error(ex.getMessage());
            }
        });
    }

    private void updateProposal(Proposal proposal) {
        proposal.setIntegrated(true);
        proposalRepository.save(proposal);
    }

}
