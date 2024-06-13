package com.wallace.proposalapp.repository;

import com.wallace.proposalapp.domain.Proposal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    @Query(value = "select * from tb_proposal as p" +
            " where p.integrated = false", nativeQuery = true)
    List<Proposal> getAllProposalsByIntegrated();
}
