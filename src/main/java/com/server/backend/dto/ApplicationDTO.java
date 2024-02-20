package com.server.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationDTO {
    private Integer applicationId;
    private String status;
    private Integer personId;
    private PersonNameDTO personName;
}