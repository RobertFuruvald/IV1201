package com.server.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.backend.dto.ApplicationSubmissionDTO;
import com.server.backend.dto.CompetenceDTO;
import com.server.backend.service.ApplyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

  @Autowired
  private ApplyService applyService;

  @GetMapping("/competences")
  public ResponseEntity<?> getCompetenceList() {
    List<CompetenceDTO> competencesList = applyService.fetchAllCompetences();
    return ResponseEntity.ok(competencesList);
  }

  @PostMapping("/apply")
  public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationSubmissionDTO application) {
    applyService.submitApplication(application);
    return ResponseEntity.status(HttpStatus.CREATED).body("Application created Successfully");
  }

}
