package com.wallace.proposalapp.scheduler;

import com.wallace.proposalapp.repository.ProposalRepository;
import com.wallace.proposalapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProposalWithoutIntegrationScheduler {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;

    private final String exchange;

    public ProposalWithoutIntegrationScheduler(ProposalRepository proposalRepository,
                                               NotificationService notificationService,
                                               @Value("${rabbitmq.pendingproposal.exchange}")  String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public void findNotIntegratedProposals() {
        proposalRepository.getAllProposalsByIntegrated().forEach(proposal -> {
            try {
                notificationService.notify(proposal, exchange);
                proposal.setIntegrated(true);
                proposalRepository.save(proposal);
            } catch (RuntimeException ex) {
                System.out.println(ex);
            }
        });
    }

}
