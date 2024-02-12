package com.server.backend.dto;

import com.server.backend.entity.Competence;

import lombok.Getter;

@Getter
public class CompetenceDTO {
    private Integer id;
    private String name;

    public CompetenceDTO(Competence competence) {
        this.id = competence.getCompetenceId();
        this.name = competence.getName();
    }
}
