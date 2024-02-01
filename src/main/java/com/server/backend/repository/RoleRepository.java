package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
