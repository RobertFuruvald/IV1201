package com.server.backend.controller;

import com.server.backend.dto.ApplicationDTO;
import com.server.backend.service.HandleApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class ApplicationsController {
    @Autowired
    private HandleApplicationsService handleApplicationsService;

    @GetMapping("/applications")
    public ResponseEntity<?> getApplications() {
        List<ApplicationDTO> applications = handleApplicationsService.fetchAllApplications();
        if (applications == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        return ResponseEntity.ok(applications);
    }
}