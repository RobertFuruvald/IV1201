package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}
