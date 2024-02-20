package com.server.backend.controller;

import com.server.backend.dto.ApplicationDTO;
import com.server.backend.dto.ApplicationResponseDTO;
import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetenceProfileInformationDTO;
import com.server.backend.service.HandleApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationsController {
    @Autowired
    private HandleApplicationsService handleApplicationsService;

    @GetMapping("/recruiter")
    public ResponseEntity<?> getApplications() {
        List<ApplicationDTO> applications = handleApplicationsService.fetchAllApplications();
        if (applications == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No applications found");
        }
        return ResponseEntity.ok(applications);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getApplication(@PathVariable Integer id) {
        ApplicationDTO application = handleApplicationsService.getApplication(id);
        List<CompetenceProfileInformationDTO> competenceProfileInformationDTOList = handleApplicationsService.fetchCompetenceProfileInformationForApplicant(application.getPersonId());
        List<AvailabilityPeriodDTO> availabilityPeriodDTOList = handleApplicationsService.fetchAllAvailabilityPeriodsForInApplicant(application.getPersonId());
        ApplicationResponseDTO response = new ApplicationResponseDTO(application, competenceProfileInformationDTOList, availabilityPeriodDTOList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getApplicationForUser(@PathVariable String username) {
        ApplicationDTO application = handleApplicationsService.getApplicationForUser(username);
        if (application == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        List<CompetenceProfileInformationDTO> competenceProfileInformationDTOList = handleApplicationsService.fetchCompetenceProfileInformationForApplicant(application.getPersonId());
        List<AvailabilityPeriodDTO> availabilityPeriodDTOList = handleApplicationsService.fetchAllAvailabilityPeriodsForInApplicant(application.getPersonId());
        ApplicationResponseDTO response = new ApplicationResponseDTO(application, competenceProfileInformationDTOList, availabilityPeriodDTOList);
        return ResponseEntity.ok(response);
    }
}