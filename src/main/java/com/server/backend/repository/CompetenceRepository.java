package com.server.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.server.backend.dto.CompetenceDTO;
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
    
    @Query("SELECT new com.server.backend.dto.CompetenceDTO(c.competenceId, c.name) FROM Competence c")
    List<CompetenceDTO> findCompetencesAsDTOs();

    @Query("SELECT new com.server.backend.dto.CompetenceDTO(c.competenceId, c.name) FROM Competence c WHERE c.competenceId = :id")
    CompetenceDTO findCompetenceByIdAsDTO(@Param("id") Integer id);
}
