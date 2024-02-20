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

    //This method may need to be overridden to prevent error from null response
    List<Application> findAll();

    Application findByApplicationId(Integer applicationId);

}