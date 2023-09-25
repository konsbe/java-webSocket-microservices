package com.kobertech.kobertechv1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kobertech.kobertechv1.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Custom query methods can be defined here
    // Spring Data JPA provides various query generation strategies and keyword-based methods
    // For example, findByEmail(String email) will generate a query to find an account by email
    // You can also define custom queries using @Query annotation
}
