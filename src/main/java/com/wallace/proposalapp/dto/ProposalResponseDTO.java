package com.wallace.proposalapp.dto;

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
    private String valorSolicitadoFmt;
    private int prazoPagamento;
    private Boolean aprovada;
    private String observacao;
}
