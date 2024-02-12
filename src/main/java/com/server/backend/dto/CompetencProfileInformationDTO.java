package com.server.backend.dto;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompetencProfileInformationDTO {

    @Valid
    private CompetenceDTO competenceDTO;

    @NotNull(message = "Years of experience must not be null")
    @DecimalMin(value = "0.1", message = "Must be a number higher than 0")
    private BigDecimal yearsOfExperience;

}
