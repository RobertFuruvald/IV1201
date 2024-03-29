package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing an application.
 * <p>
 * Contains foreign keys for the person.
 * <p>
 * The {@link Data} annotation from Lombok is used to automatically generate
 * getters, setters, and other common methods like {@code toString},
 * {@code equals}, and {@code hashCode}.
 */
@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    /**
     * The unique identifier for an application. This ID is automatically
     * generated by
     * the database when a new Application is created.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    private String status;

    @Column(name = "person_id")
    private Integer personId;
}