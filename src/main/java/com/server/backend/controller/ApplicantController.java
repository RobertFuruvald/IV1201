package com.server.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.backend.dto.ApplicationDTO;
import com.server.backend.dto.ApplicationResponseDTO;
import com.server.backend.dto.ApplicationSubmissionDTO;
import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetenceDTO;
import com.server.backend.dto.CompetenceProfileInformationDTO;
import com.server.backend.service.ApplyService;
import com.server.backend.service.HandleApplicationsService;

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
  private HandleApplicationsService handleApplicationsService;

  @Autowired
  private ApplyService applyService;

  @GetMapping("/competences")
  public ResponseEntity<?> getCompetenceList() {
    List<CompetenceDTO> competencesList = applyService.fetchAllCompetences();
    return ResponseEntity.ok(competencesList);
  }

  @PostMapping("/apply")
  public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationSubmissionDTO application) {
    System.out.println(application.toString());
    applyService.submitApplication(application);
    return ResponseEntity.status(HttpStatus.CREATED).body("Application created Successfully");
  }

  @GetMapping("/application")
  public ResponseEntity<?> getApplicationForUser() {
    ApplicationDTO application = handleApplicationsService.getApplicationForUser();
    if (application == null) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
    }
    List<CompetenceProfileInformationDTO> competenceProfileInformationDTOList = handleApplicationsService
        .fetchCompetenceProfileInformationForApplicant(application.getPersonId());
    List<AvailabilityPeriodDTO> availabilityPeriodDTOList = handleApplicationsService
        .fetchAllAvailabilityPeriodsForApplicant(application.getPersonId());
    ApplicationResponseDTO response = new ApplicationResponseDTO(application, competenceProfileInformationDTOList,
        availabilityPeriodDTOList);
    return ResponseEntity.ok(response);
  }

}
