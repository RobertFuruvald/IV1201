package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.CompetenceProfile;

/**
 * Competence Profile repository interface for handling persistence operations on
 * {@link CompetenceProfile}
 * entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Integer> {
}
