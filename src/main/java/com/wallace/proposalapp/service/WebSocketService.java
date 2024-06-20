package com.wallace.proposalapp.service;

import com.wallace.proposalapp.dto.ProposalResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public void notify(ProposalResponseDTO proposalResponseDTO) {
        template.convertAndSend("/propostas", proposalResponseDTO);
    }
}
