package com.server.backend.dto;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class ApplicationSubmissionDTO {
    @Valid
    private CompetencProfileInformationDTO competencProfileInformationDTO;
    @Valid
    private AvailabilityPeriodDTO availabilityPeriodDTO;

}
