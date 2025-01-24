package org.example.javaspring.bikerental.services;

import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    public Bike findById(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void deleteById(Long id) {
        bikeRepository.deleteById(id);
    }
}
