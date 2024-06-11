package com.wallace.proposalapp.dto;

import com.wallace.proposalapp.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalDTO {
    private Long id;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private boolean integrado;
    private String observacao;
    private User usuario;
}
