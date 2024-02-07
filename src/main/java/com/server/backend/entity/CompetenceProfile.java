package com.server.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a competence profile .
 * <p>
 * The {@link Data} annotation from Lombok is used to automatically generate
 * getters, setters, and other common methods like {@code toString},
 * {@code equals}, and {@code hashCode}.
 */
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
