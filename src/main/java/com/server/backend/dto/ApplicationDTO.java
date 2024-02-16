package com.server.backend.dto;

import lombok.Getter;

@Getter
public class ApplicationDTO {
    private Integer applicationId;
    private String status;
    private Integer personId;
    private String firstName;
    private String lastName;

    public ApplicationDTO(Integer applicationId, String status, Integer personId, PersonNameDTO personName) {
        this.applicationId = applicationId;
        this.status = status;
        this.personId = personId;
        this.firstName = personName.getFirstName();
        this.lastName = personName.getLastName();
    }
}