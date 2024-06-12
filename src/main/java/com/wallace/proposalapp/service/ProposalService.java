package com.wallace.proposalapp.service;

import com.wallace.proposalapp.converter.ProposalConverter;
import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.dto.ProposalRequestDTO;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import com.wallace.proposalapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProposalConverter proposalConverter;

    public ProposalResponseDTO createProposal(ProposalRequestDTO requestDTO) {
        Proposal proposal = proposalConverter.from(requestDTO);
        proposalRepository.save(proposal);

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
}
