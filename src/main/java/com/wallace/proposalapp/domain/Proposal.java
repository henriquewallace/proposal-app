package com.wallace.proposalapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;
    private int paymentTerm;
    private Boolean approved;
    private boolean integrated;
    private String observation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private User user;
}
