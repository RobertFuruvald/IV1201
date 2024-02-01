package com.server.backend.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.backend.dto.RegistrationDTO;
import com.server.backend.entity.Person;
import com.server.backend.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistrationService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Integer DEFAULT_ROLE_ID = 2;

    @Transactional
    public void registerNewApplicant(RegistrationDTO applicantRegDetails) {

        //We need to add exception handling and field validation here
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
