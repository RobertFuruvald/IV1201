package com.server.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetenceProfileInformationDTO;
import com.server.backend.repository.AvailabilityRepository;
import com.server.backend.repository.CompetenceProfileRepository;
import com.server.backend.repository.CompetenceRepository;

@Service
public class HandleApplicationsService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<CompetenceProfileInformationDTO> fetchCompetenceProfileInformationForApplicant(Integer applicantId) {
        return competenceProfileRepository.findByPersonId(applicantId).stream()
                .map(profile -> new CompetenceProfileInformationDTO(
                        competenceRepository.findCompetenceByIdAsDTO(profile.getCompetenceId()),
                        profile.getYearsOfExperience()))
                .collect(Collectors.toList());
    }

    public List<AvailabilityPeriodDTO> fetchAllAvailabilityPeriodsForInApplicant(Integer applicantId) {

        return availabilityRepository.findByPersonId(applicantId).stream()
                .map(availability -> new AvailabilityPeriodDTO(availability.getFromDate(), availability.getToDate()))
                .collect(Collectors.toList());
    }

}
