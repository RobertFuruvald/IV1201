package com.server.backend.dto;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class ApplicationSubmissionDTO {
    @Valid
    private List<CompetencProfileInformationDTO> competencProfileInformationDTO;
    @Valid
    private List<AvailabilityPeriodDTO> availabilityPeriodDTO;

}
