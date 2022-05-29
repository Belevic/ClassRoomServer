package com.example.classroom.repository;

import com.example.classroom.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * User repository for database work
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);
    void deleteUserByName(String name);
}
