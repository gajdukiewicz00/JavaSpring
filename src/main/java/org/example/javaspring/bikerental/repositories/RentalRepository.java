package org.example.javaspring.bikerental.repositories;

import org.example.javaspring.bikerental.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
