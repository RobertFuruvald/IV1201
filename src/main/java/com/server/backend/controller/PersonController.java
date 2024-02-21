package com.server.backend.controller;

import com.server.backend.dto.PersonUpdateDTO;
import com.server.backend.entity.Person;
import com.server.backend.exception.ResourceNotFoundException;
import com.server.backend.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// mapping as needed
@RequestMapping("/api/persons") 
public class PersonController {

    @Autowired
    private PersonService personService;

    // Here it is the Endpoint for updating person information
    @PutMapping("/update")
    public ResponseEntity<String> updatePersonInformation(@Valid @RequestBody PersonUpdateDTO updateDTO) {
        try {
            if (updateDTO.getEmail().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
            }

            Person updatedPerson = personService.updatePersonInformation(updateDTO.getEmail(), updateDTO);

            if (updatedPerson != null) {
                return ResponseEntity.ok("Person information updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with the given email");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
