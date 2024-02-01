package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Person;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
}
