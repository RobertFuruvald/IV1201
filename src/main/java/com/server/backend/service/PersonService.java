package com.server.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.server.backend.entity.Person;
import com.server.backend.dto.PersonUpdateDTO;
import com.server.backend.repository.PersonRepository;

import java.util.Optional;

/**
 * Service class for updating person information.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

      /**
     * update all information that needed in person
     * 
     * @param username
     * @param updateDTO The DTO containing the updated information.
     * @return The updated person object if successful, otherwise null.
     */

      public Person updatePersonInformation(String username, PersonUpdateDTO updateDTO) {
          Optional<Person> optionalPerson = personRepository.findByUsername(username);

          if (optionalPerson.isPresent()) {
              Person person = optionalPerson.get();
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
                  // Encode the new password using the PasswordEncoder that we have.
                  String encodedPassword = passwordEncoder.encode(updateDTO.getPassword());
                  person.setPassword(encodedPassword);
              }
              // Save all information about the updated person in the repository and return it.
              return personRepository.save(person);
          } else {
              return null;
          }
      }

}
