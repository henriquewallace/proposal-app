package com.wallace.proposalapp.repository;

import com.wallace.proposalapp.domain.Proposal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    @Query(value = "select * from tb_proposal as p" +
            " where p.integrated = false", nativeQuery = true)
    List<Proposal> getAllProposalsByIntegrated();

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_proposal SET approved = :approved, observation = :observation where id = :id", nativeQuery = true)
    void updateProposal(Long id, boolean approved, String observation);
}
