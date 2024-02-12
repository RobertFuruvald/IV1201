package com.server.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.server.backend.dto.ApplicationSubmissionDTO;
import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetencProfileInformationDTO;
import com.server.backend.dto.CompetenceDTO;
import com.server.backend.entity.Availability;
import com.server.backend.entity.CompetenceProfile;
import com.server.backend.repository.AvailabilityRepository;
import com.server.backend.repository.CompetenceProfileRepository;
import com.server.backend.repository.CompetenceRepository;
import com.server.backend.security.CustomUserDetailsPrincipal;

import jakarta.transaction.Transactional;

/**
 * Service class to handle application submission by applicants.
 * It includes operations to fetch available competences,
 * add personal competence and availability periods, and submit the entire
 * application.
 */
@Transactional
@Service
public class ApplyService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * Fetches all competences available for applicants to choose from.
     * 
     * @return a list of {@link CompetenceDTO} representing all available
     *         competences.
     */
    public List<CompetenceDTO> fetchAllCompetences() {
        return competenceRepository.findCompetencesAsDTOs();
    }

    /**
     * Submits an application with the specified competences and availability periods.
     * It internally adds personal competence and availability periods for the currently authenticated user.
     * 
     * @param application the {@link ApplicationSubmissionDTO} containing competence and availability information.
     */
    public void submitApplication(ApplicationSubmissionDTO application) {
        addPersonalCompetence(application.getCompetencProfileInformationDTO());
        addAvailabilityPeriod(application.getAvailabilityPeriodDTO());
        return;
    }

    private void addPersonalCompetence(CompetencProfileInformationDTO competenceProfileDTO) {
        CompetenceProfile personalCompetenceEntry = new CompetenceProfile();
        personalCompetenceEntry.setCompetenceId(competenceProfileDTO.getCompetenceDTO().getCompetenceId());
        personalCompetenceEntry.setYearsOfExperience(competenceProfileDTO.getYearsOfExperience());
        personalCompetenceEntry.setPersonId(getAuthenticatedUserDetails().getPersonId());
        competenceProfileRepository.save(personalCompetenceEntry);
    }

    private void addAvailabilityPeriod(AvailabilityPeriodDTO availabilityDTO) {
        Availability newAvailabilityPeriod = new Availability();
        newAvailabilityPeriod.setFromDate(availabilityDTO.getFromDate());
        newAvailabilityPeriod.setToDate(availabilityDTO.getToDate());
        newAvailabilityPeriod.setPersonId(getAuthenticatedUserDetails().getPersonId());
        availabilityRepository.save(newAvailabilityPeriod);
    }

    private CustomUserDetailsPrincipal getAuthenticatedUserDetails() {
        CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return userDetails;
    }
}
