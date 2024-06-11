package com.wallace.proposalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String cpf;
    private String phoneNumber;
    private Double income;

    @OneToOne(mappedBy = "user")
    private Proposal proposal;
}
