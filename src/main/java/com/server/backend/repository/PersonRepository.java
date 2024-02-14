package com.server.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.backend.entity.Person;
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

    Person findByPersonId(Integer personId);

}
