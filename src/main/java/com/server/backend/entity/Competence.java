package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competenceId;

    private String name;
}
