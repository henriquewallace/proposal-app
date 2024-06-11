package com.wallace.proposalapp.dto;

import com.wallace.proposalapp.domain.Proposal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Double renda;
    private Proposal proposta;
}
