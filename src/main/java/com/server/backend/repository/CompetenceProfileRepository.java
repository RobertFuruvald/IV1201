package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.CompetenceProfile;

public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Integer> {
}
