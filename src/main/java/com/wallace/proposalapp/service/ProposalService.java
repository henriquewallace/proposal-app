package com.wallace.proposalapp.service;

import com.wallace.proposalapp.dto.ProposalRequestDTO;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    public ProposalResponseDTO createProposal(ProposalRequestDTO requestDTO) {
        ProposalResponseDTO responseDTO = new ProposalResponseDTO();

        return responseDTO;
    }
}
