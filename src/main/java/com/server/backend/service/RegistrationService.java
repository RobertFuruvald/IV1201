package com.server.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.backend.dto.RegistrationDTO;
import com.server.backend.entity.Person;
import com.server.backend.repository.PersonRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for handling registration of new applicants.
 * <p>
 * This service provides functionality to register new users in the system,
 * including password encoding and role assignment
 */
@Service
public class RegistrationService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Integer DEFAULT_ROLE_ID = 2;

    /**
     * Registers a new person as an applicant with the given registration details.
     * <p>
     * This method encodes the password and sets the users role to applicant bafore
     * saving the new person entity using {@link PersonRepository}.
     * 
     * @param applicantRegDetails {@link RegistrationDTO} containing user registration details.
     */
    @Transactional
    public void registerNewApplicant(RegistrationDTO applicantRegDetails) {

        // We need to add exception handling and field validation here
        Person newApplicant = new Person();
        newApplicant.setName(applicantRegDetails.getName());
        newApplicant.setSurname(applicantRegDetails.getSurname());
        newApplicant.setPnr(applicantRegDetails.getPnr());
        newApplicant.setEmail(applicantRegDetails.getEmail());
        newApplicant.setPassword(passwordEncoder.encode(applicantRegDetails.getPassword()));
        newApplicant.setUsername(applicantRegDetails.getUsername());
        newApplicant.setRoleId(DEFAULT_ROLE_ID);

        personRepository.save(newApplicant);
    }

}
