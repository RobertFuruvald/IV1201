package com.server.backend.controller;

import com.server.backend.dto.PersonUpdateDTO;
import com.server.backend.exception.ResourceNotFoundException;
import com.server.backend.service.PersonService;
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
    @PutMapping("/{personId}")
    public ResponseEntity<String> updatePersonInformation(@PathVariable Integer personId,
                                                          @RequestBody PersonUpdateDTO updateDTO) {
        try {
            personService.updatePersonInformation(personId, updateDTO);
            return ResponseEntity.ok("Person information updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
