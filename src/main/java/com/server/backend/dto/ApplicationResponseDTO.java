package com.server.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationResponseDTO {
    private ApplicationDTO application;
    private List<CompetenceProfileInformationDTO> competenceProfileInformationDTOList;
    private List<AvailabilityPeriodDTO> availabilityPeriodDTOList;
}