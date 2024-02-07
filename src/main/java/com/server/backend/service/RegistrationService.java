package com.server.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.backend.dto.RegistrationDTO;
import com.server.backend.entity.Person;
import com.server.backend.exception.CustomValidationException;
import com.server.backend.repository.PersonRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for handling the registration of new applicants.
 * <p>
 * Provides functionality to securely register new users in the system by
 * encoding passwords,
 * assigning default roles, and ensuring uniqueness of username and email.
 * </p>
 */
@Service
public class RegistrationService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Integer DEFAULT_ROLE_ID = 2;

    /**
     * Registers a new person as an applicant with the provided registration
     * details.
     * <p>
     * Validates the uniqueness of the username and email against existing records
     * in the data source to prevent duplicates. If either the username or email
     * already exists, a {@link CustomValidationException} is thrown. Upon
     * successful validation, the applicant's password is encoded, and the applicant
     * is assigned the correct role before being saved to the database.
     * </p>
     * 
     * @param applicantRegDetails {@link RegistrationDTO} containing the
     *                            registration details of the new applicant.
     * @throws CustomValidationException if the username or email provided in
     *                                   {@code applicantRegDetails}
     *                                   already exists in the database.
     */
    @Transactional
    public void registerNewApplicant(RegistrationDTO applicantRegDetails) {

        if (personRepository.findByUsername(applicantRegDetails.getUsername()).isPresent()) {
            throw new CustomValidationException("Username already exists");
        }

        if (personRepository.findByEmail(applicantRegDetails.getEmail()).isPresent()) {
            throw new CustomValidationException("Email already exists");
        }
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
