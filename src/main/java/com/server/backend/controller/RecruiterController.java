package com.server.backend.controller;

import com.server.backend.dto.ApplicationDTO;
import com.server.backend.dto.ApplicationResponseDTO;
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
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private HandleApplicationsService handleApplicationsService;

    @GetMapping("/applications")
    public ResponseEntity<?> getAllApplications() {
        List<ApplicationDTO> applications = handleApplicationsService.fetchAllApplications();
        if (applications == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No applications found");
        }
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/applications/{userId}")
    public ResponseEntity<?> getUsersApplication(@PathVariable Integer userId) {
        ApplicationResponseDTO response = handleApplicationsService.getApplicationResponseForUser(userId);
        return ResponseEntity.ok(response);
    }
}