package com.server.backend.repository;

import com.server.backend.dto.PersonNameDTO;
import com.server.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Person repository interface for handling persistence operations on
 * {@link Person} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD.
 * Additional query methods can be defined as needed.
 * </p>
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);

    @Query("SELECT NEW com.server.backend.dto.PersonNameDTO(p.name, p.surname) FROM Person p WHERE p.id = :id")
    PersonNameDTO findPersonNameById(@Param("id") Integer id);
}