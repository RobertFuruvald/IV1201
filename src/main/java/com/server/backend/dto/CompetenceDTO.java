package com.server.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompetenceDTO {

    @NotNull(message = "Id of competence must be provided")
    private Integer competenceId;

    @NotBlank(message = "name of competence must be provided")
    private String name;
}
