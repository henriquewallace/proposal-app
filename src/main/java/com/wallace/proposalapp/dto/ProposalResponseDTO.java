package com.wallace.proposalapp.dto;

import com.wallace.proposalapp.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private String observacao;
}
