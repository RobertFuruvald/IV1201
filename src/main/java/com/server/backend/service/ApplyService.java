package com.server.backend.service;

import com.server.backend.dto.ApplicationSubmissionDTO;
import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetenceDTO;
import com.server.backend.dto.CompetenceProfileInformationDTO;
import com.server.backend.entity.Application;
import com.server.backend.entity.Availability;
import com.server.backend.entity.CompetenceProfile;
import com.server.backend.repository.ApplicationRepository;
import com.server.backend.repository.AvailabilityRepository;
import com.server.backend.repository.CompetenceProfileRepository;
import com.server.backend.repository.CompetenceRepository;
import com.server.backend.security.CustomUserDetailsPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ApplicationRepository applicationRepository;

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
     * Submits an application with the specified competences and availability
     * periods.
     * It internally adds personal competence and availability periods for the
     * currently authenticated user.
     *
     * @param application the {@link ApplicationSubmissionDTO} containing competence
     *                    and availability information.
     */
    public void submitApplication(ApplicationSubmissionDTO application) {
        addPersonalCompetence(application.getCompetenceProfileInformationDTOs());
        addAvailabilityPeriod(application.getAvailabilityPeriodDTOs());
        Integer personId = getAuthenticatedUserDetails().getPersonId();
        Application apply = new Application();
        apply.setPersonId(personId);
        if (applicationRepository.findByPersonId(personId) != null) {
            applicationRepository.save(apply);
        }
    }

    private void addPersonalCompetence(List<CompetenceProfileInformationDTO> competenceProfileDTOs) {
        Integer personId = getAuthenticatedUserDetails().getPersonId();
        List<CompetenceProfile> competenceProfiles = competenceProfileDTOs.stream()
                .map(dto -> new CompetenceProfile(null, personId, dto.getCompetenceDTO().getCompetenceId(),
                        dto.getYearsOfExperience()))
                .collect(Collectors.toList());
        competenceProfileRepository.saveAll(competenceProfiles);
    }

    private void addAvailabilityPeriod(List<AvailabilityPeriodDTO> availabilityDTOs) {
        Integer personId = getAuthenticatedUserDetails().getPersonId();
        List<Availability> availabilityPeriods = availabilityDTOs.stream()
                .map(dto -> new Availability(null, personId, dto.getFromDate(), dto.getToDate()))
                .collect(Collectors.toList());
        availabilityRepository.saveAll(availabilityPeriods);
    }

    private CustomUserDetailsPrincipal getAuthenticatedUserDetails() {
        CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return userDetails;
    }
}