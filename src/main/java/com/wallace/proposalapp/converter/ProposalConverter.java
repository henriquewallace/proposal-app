package com.wallace.proposalapp.converter;

import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.domain.User;
import com.wallace.proposalapp.dto.ProposalRequestDTO;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class ProposalConverter {

    public Proposal from(ProposalRequestDTO proposalRequestDTO) {
        Proposal proposal = new Proposal();

        proposal.setId(null);
        proposal.setRequestedAmount(proposalRequestDTO.getValorSolicitado());
        proposal.setPaymentTerm(proposalRequestDTO.getPrazoPagamento());
        proposal.setApproved(null);
        proposal.setIntegrated(true);
        proposal.setObservation(null);

        User user = new User();
        user.setName(proposalRequestDTO.getNome());
        user.setLastName(proposalRequestDTO.getSobrenome());
        user.setCpf(proposalRequestDTO.getCpf());
        user.setPhoneNumber(proposalRequestDTO.getTelefone());
        user.setIncome(proposalRequestDTO.getRenda());
        proposal.setUser(user);

        return proposal;
    }

    public ProposalResponseDTO from(Proposal proposal) {
        ProposalResponseDTO proposalResponseDTO = new ProposalResponseDTO();
        var user = proposal.getUser();

        proposalResponseDTO.setId(proposal.getId());
        proposalResponseDTO.setNome(user.getName());
        proposalResponseDTO.setSobrenome(user.getLastName());
        proposalResponseDTO.setTelefone(user.getPhoneNumber());
        proposalResponseDTO.setCpf(user.getCpf());
        proposalResponseDTO.setRenda(user.getIncome());
        proposalResponseDTO.setValorSolicitadoFmt(formatRequestedAmount(proposal));
        proposalResponseDTO.setPrazoPagamento(proposal.getPaymentTerm());
        proposalResponseDTO.setAprovada(proposal.getApproved());
        proposalResponseDTO.setObservacao(proposal.getObservation());

        return proposalResponseDTO;
    }

    private String formatRequestedAmount(Proposal proposal) {
        return NumberFormat.getCurrencyInstance().format(proposal.getRequestedAmount());
    }
}
