package com.wallace.proposalapp.service;

import com.wallace.proposalapp.dto.ProposalResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    private RabbitTemplate rabbitTemplate;

    public void notify(ProposalResponseDTO proposalResponseDTO, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposalResponseDTO);
    }
}
