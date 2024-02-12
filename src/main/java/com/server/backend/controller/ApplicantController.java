package com.server.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.backend.dto.CompetenceDTO;
import com.server.backend.service.CompetenceService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/applicant/apply")
public class ApplicantController {

    @Autowired
    private CompetenceService competenceService;

  @GetMapping("/competences")
    public ResponseEntity<?> getCompetenceList() {
        List<CompetenceDTO> competencesList = competenceService.fetchAllCompetences();
        return ResponseEntity.ok(competencesList);
    }

}
