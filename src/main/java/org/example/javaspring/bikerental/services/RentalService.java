package org.example.javaspring.bikerental.services;

import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.entities.Rental;
import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.example.javaspring.bikerental.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BikeRepository bikeRepository;

    public RentalService(RentalRepository rentalRepository, BikeRepository bikeRepository) {
        this.rentalRepository = rentalRepository;
        this.bikeRepository = bikeRepository;
    }

    public Rental startRental(User user, Bike bike) {
        if (bike.isRented()) {
            return null; // велосипед уже в аренде
        }
        bike.setRented(true);
        bikeRepository.save(bike);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBike(bike);
        rental.setStartDate(LocalDateTime.now());
        return rentalRepository.save(rental);
    }

    public Rental finishRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElse(null);
        if (rental == null) {
            return null;
        }
        rental.setEndDate(LocalDateTime.now());
        Bike bike = rental.getBike();
        bike.setRented(false);
        bikeRepository.save(bike);

        return rentalRepository.save(rental);
    }
}
