package org.example.javaspring.bikerental.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.entities.Rental;
import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.services.BikeService;
import org.example.javaspring.bikerental.services.RentalService;
import org.example.javaspring.bikerental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BikeController {

    private final BikeService bikeService;
    private final RentalService rentalService;
    private final UserService userService;

    public BikeController(BikeService bikeService, RentalService rentalService, UserService userService) {
        this.bikeService = bikeService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("bikes", bikeService.findAll());
        return "index";
    }

    @GetMapping("/bikes/{id}")
    public String bikeDetail(@PathVariable Long id, Model model) {
        Bike bike = bikeService.findById(id);
        if (bike == null) {
            return "redirect:/";
        }
        model.addAttribute("bike", bike);
        return "bike-detail";
    }


    @PostMapping("/bikes/{id}/rent")
    public String rentBike(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/auth/login";
        }
        Bike bike = bikeService.findById(id);
        if (bike == null) {
            return "redirect:/";
        }
        User user = userService.findById(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        Rental rental = rentalService.startRental(user, bike);
        return "redirect:/";
    }
}
