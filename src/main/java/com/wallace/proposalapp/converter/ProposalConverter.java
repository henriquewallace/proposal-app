package com.wallace.proposalapp.converter;

import com.wallace.proposalapp.domain.Proposal;
import com.wallace.proposalapp.dto.ProposalDTO;

public class ProposalConverter {

    public Proposal from(ProposalDTO proposalDTO) {
        Proposal proposal = new Proposal();
        proposal.setId(proposalDTO.getId());
        proposal.setRequestedAmount(proposalDTO.getValorSolicitado());
        proposal.setPaymentTerm(proposalDTO.getPrazoPagamento());
        proposal.setApproved(proposalDTO.getAprovado());
        proposal.setIntegrated(proposalDTO.isIntegrado());
        proposal.setObservation(proposalDTO.getObservacao());
        proposal.setUser(proposalDTO.getUsuario());

        return proposal;
    }

    public ProposalDTO from(Proposal proposal) {
        ProposalDTO proposalDTO = new ProposalDTO();
        proposalDTO.setId(proposal.getId());
        proposalDTO.setValorSolicitado(proposal.getRequestedAmount());
        proposalDTO.setPrazoPagamento(proposal.getPaymentTerm());
        proposalDTO.setAprovado(proposal.getApproved());
        proposalDTO.setIntegrado(proposal.isIntegrated());
        proposalDTO.setObservacao(proposal.getObservation());
        proposalDTO.setUsuario(proposal.getUser());

        return proposalDTO;
    }
}
