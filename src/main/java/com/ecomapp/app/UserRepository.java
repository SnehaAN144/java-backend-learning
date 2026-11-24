package com.ecomapp.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring-managed repository component
public interface UserRepository extends JpaRepository<User, Long> {

    // User  → Entity type this repository manages
    // Long  → Data type of the entity's primary key (id)

    // JpaRepository provides ready-made database operations:
    // save(), findById(), findAll(), deleteById(), count(), etc.
}