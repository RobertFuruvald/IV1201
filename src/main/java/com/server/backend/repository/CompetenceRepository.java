package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Competence;


/**
 * Competence repository interface for handling persistence operations on
 * {@link Competence}
 * entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}
