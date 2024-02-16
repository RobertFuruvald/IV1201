package com.server.backend.repository;

import com.server.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Person repository interface for handling persistence operations on
 * {@link Application} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByPersonId(Integer id);

    List<Application> findAll();
}