package org.example.javaspring.bikerental.controllers;

import org.example.javaspring.bikerental.repositories.RentalRepository;
import org.example.javaspring.bikerental.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RentalController {

    private final UserRepository userRepo;
    private final RentalRepository rentalRepo;

    public RentalController(UserRepository userRepo, RentalRepository rentalRepo) {
        this.userRepo = userRepo;
        this.rentalRepo = rentalRepo;
    }

    @GetMapping("/my")
    public String myRentals() {
        return "rentals";
    }
}

