package com.server.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.backend.dto.CompetenceDTO;
import com.server.backend.repository.CompetenceRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<CompetenceDTO> fetchAllCompetences() {
        return competenceRepository.findAll().stream().map(competence -> new CompetenceDTO(competence))
                .collect(Collectors.toList());
    }

}
