package com.server.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class ApplicationSubmissionDTO {
    @JsonProperty("competenceProfileInformationDTOs")
    @Valid
    private List<CompetenceProfileInformationDTO> competenceProfileInformationDTOs;

    @JsonProperty("availabilityPeriodDTOs")
    @Valid
    private List<AvailabilityPeriodDTO> availabilityPeriodDTOs;

}
