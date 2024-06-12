package com.wallace.proposalapp.controller;

import com.wallace.proposalapp.dto.ProposalRequestDTO;
import com.wallace.proposalapp.dto.ProposalResponseDTO;
import com.wallace.proposalapp.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class ProposalController {

    private ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponseDTO> createProposal(@RequestBody ProposalRequestDTO requestDTO) {
        ProposalResponseDTO responseDTO = proposalService.createProposal(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri())
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProposalResponseDTO>> getAllProposals() {
        return ResponseEntity.ok(proposalService.getAllProposals());
    }
}
