package org.example.javaspring.bikerental.repositories;

import org.example.javaspring.bikerental.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
