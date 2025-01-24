package org.example.javaspring.bikerental.repositories;

import org.example.javaspring.bikerental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
