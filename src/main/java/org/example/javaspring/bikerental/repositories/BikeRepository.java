package org.example.javaspring.bikerental.repositories;

import org.example.javaspring.bikerental.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
