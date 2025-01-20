package org.example.javaspring.bikerental.controllers;

import org.example.javaspring.bikerental.entities.Rental;
import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.repositories.RentalRepository;
import org.example.javaspring.bikerental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/all")
    public String listAllRentals(Model model) {
        List<Rental> rentals = rentalRepo.findAll();
        model.addAttribute("rentals", rentals);
        return "rentals_list"; // шаблон rentals_list.html
    }


    @GetMapping("/my")
    public String myRentals(Principal principal, Model model) {
        String username = principal.getName();

        Optional<User> optionalUser = userRepo.findByName(username);

        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }

        User currentUser = optionalUser.get();

        List<Rental> myList = rentalRepo.findAll().stream()
                .filter(r -> r.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());

        model.addAttribute("rentals", myList);

        return "rentals_my";
    }


    @PostMapping("/rent")
    public String rentBike(@RequestParam Long bicycleId, Principal principal) {
        Optional<User> optionalUser = userRepo.findByName(principal.getName());
        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalUser.get();


        Rental rental = new Rental();
        Long maxId = rentalRepo.findAll().stream()
                .mapToLong(Rental::getRentalId)
                .max()
                .orElse(0L);
        rental.setRentalId(maxId + 1);

        rental.setUserId(currentUser.getUserId());
        rental.setBicycleId(bicycleId);
        rental.setStartdate(LocalDateTime.now());

        rentalRepo.save(rental);

        return "redirect:/rentals/my";
    }


    @PostMapping("/return")
    public String returnBike(@RequestParam Long rentalId) {
        Rental rental = rentalRepo.findById(rentalId).orElse(null);
        if (rental != null && rental.getEnddate() == null) {
            rental.setEnddate(LocalDateTime.now());

            double cost = calculateCost(rental.getStartdate(), rental.getEnddate());
            rental.setCost(cost);

            rentalRepo.save(rental);
        }
        return "redirect:/rentals/my";
    }


    private double calculateCost(LocalDateTime start, LocalDateTime end) {
        long minutes = java.time.Duration.between(start, end).toMinutes();
        double hours = minutes / 60.0;
        return hours * 15.0;
    }
}
