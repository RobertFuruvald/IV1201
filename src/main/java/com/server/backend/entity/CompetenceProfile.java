package com.server.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competence_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competenceProfileId;

    @Column(name = "person_id")
    private Integer personId;
    
    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "years_of_experience")
    private BigDecimal yearsOfExperience;
}
