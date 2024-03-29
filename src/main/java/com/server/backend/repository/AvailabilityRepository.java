package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.server.backend.entity.Availability;
import java.util.List;


/**
 * Availability repository interface for handling persistence operations on
 * {@link Availability}
 * entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    @Query("")
    List<Availability> findByPersonId(Integer personId);
}
