package com.server.backend.service;

import com.server.backend.dto.ApplicationDTO;
import com.server.backend.dto.ApplicationResponseDTO;
import com.server.backend.dto.AvailabilityPeriodDTO;
import com.server.backend.dto.CompetenceProfileInformationDTO;
import com.server.backend.dto.PersonNameDTO;
import com.server.backend.entity.Application;
import com.server.backend.entity.Person;
import com.server.backend.exception.ResourceNotFoundException;
import com.server.backend.repository.*;
import com.server.backend.security.CustomUserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class HandleApplicationsService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PrincipalService principalService;

    public List<ApplicationDTO> fetchAllApplications() {
        String role = principalService.getAuthenticatedUserDetails().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList().get(0);
        if (Objects.equals(role, "recruiter")) {
            ArrayList<ApplicationDTO> applicationsDTO = new ArrayList<>();
            List<Application> applications = applicationRepository.findAll();
            for (Application application : applications) {
                PersonNameDTO personNameDTO = personRepository.findPersonNameById(application.getPersonId());
                applicationsDTO.add(new ApplicationDTO(
                        application.getApplicationId(),
                        application.getStatus(),
                        application.getPersonId(),
                        personNameDTO));
            }
            return applicationsDTO;
        } else {
            return null;
        }
    }

    public ApplicationDTO getApplication(Integer applicationId) {
        Application application = applicationRepository.findByApplicationId(applicationId);
        PersonNameDTO personNameDTO = personRepository.findPersonNameById(application.getPersonId());
        return new ApplicationDTO(application.getApplicationId(), application.getStatus(), application.getPersonId(),
                personNameDTO);
    }

    public ApplicationResponseDTO getApplicationResponseForUser(Integer userId) {
        Optional<Application> applicationOpt = applicationRepository.findByPersonId(userId);
        Application application = applicationOpt.orElseThrow(
                () -> new ResourceNotFoundException("No application for user with id: " + userId + " found"));

        PersonNameDTO personNameDTO = personRepository.findPersonNameById(userId);
        ApplicationDTO applicationDTO = new ApplicationDTO(application.getApplicationId(), application.getStatus(),
                application.getPersonId(),
                personNameDTO);

        List<CompetenceProfileInformationDTO> competenceProfileInformationDTOList = fetchCompetenceProfileInformationForApplicant(
                userId);

        List<AvailabilityPeriodDTO> availabilityPeriodDTOList = fetchAllAvailabilityPeriodsForApplicant(userId);

        return new ApplicationResponseDTO(applicationDTO, competenceProfileInformationDTOList,
                availabilityPeriodDTOList);
    }

    private List<CompetenceProfileInformationDTO> fetchCompetenceProfileInformationForApplicant(Integer applicantId) {
        return competenceProfileRepository.findByPersonId(applicantId).stream()
                .map(profile -> new CompetenceProfileInformationDTO(
                        competenceRepository.findCompetenceByIdAsDTO(profile.getCompetenceId()),
                        profile.getYearsOfExperience()))
                .collect(Collectors.toList());
    }

    private List<AvailabilityPeriodDTO> fetchAllAvailabilityPeriodsForApplicant(Integer applicantId) {

        return availabilityRepository.findByPersonId(applicantId).stream()
                .map(availability -> new AvailabilityPeriodDTO(availability.getFromDate(), availability.getToDate()))
                .collect(Collectors.toList());
    }

}