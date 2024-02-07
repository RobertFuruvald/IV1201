package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Role;

/**
 * Role repository interface for handling persistence operations on {@link Role}
 * entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD operations.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
