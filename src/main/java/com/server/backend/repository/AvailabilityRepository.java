package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
