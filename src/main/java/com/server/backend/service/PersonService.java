package com.server.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.backend.dto.RegistrationDTO;
import com.server.backend.entity.Person;
import com.server.backend.repository.PersonRepository;

import java.util.Optional;

/**
 * Service class for updating person information.
 */
@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * update all information that needed in person
     *
     * @param email     The email of the person to update.
     * @param updateDTO The DTO containing the updated information.
     * @return The updated person object if successful, otherwise null.
     */
    public Person updatePersonInformation(String email, RegistrationDTO updateDTO) {
        Optional<Person> optionalPerson = personRepository.findByEmail(email);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            updatePersonFields(person, updateDTO);
            return personRepository.save(person);
        } else {
            return null;
        }
    }

    private void updatePersonFields(Person person, RegistrationDTO updateDTO) {
        if (updateDTO.getName() != null) {
            person.setName(updateDTO.getName());
        }
        if (updateDTO.getUsername() != null) {
            person.setUsername(updateDTO.getUsername());
        }
        if (updateDTO.getSurname() != null) {
            person.setSurname(updateDTO.getSurname());
        }
        if (updateDTO.getEmail() != null) {
            person.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getPnr() != null) {
            person.setPnr(updateDTO.getPnr());
        }
        if (updateDTO.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(updateDTO.getPassword());
            person.setPassword(encodedPassword);
        }
    }

}
