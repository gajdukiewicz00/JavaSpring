package org.example.javaspring.bikerental.repositories;

import org.example.javaspring.bikerental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login); // Используйте login для поиска
}
