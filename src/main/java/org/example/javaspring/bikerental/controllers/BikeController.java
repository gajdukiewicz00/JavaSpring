package org.example.javaspring.bikerental.controllers;

import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping
    public String listBikes(Model model) {
        var bikes = bikeRepository.findAll();
        model.addAttribute("bikes", bikes);
        return "bikes";
    }

    @GetMapping("/{id}")
    public String bikeDetails(@PathVariable("id") Long bikeId, Model model) {
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bike id: " + bikeId));

        model.addAttribute("bike", bike);
        model.addAttribute("title", bike.getName() + " - Аренда Велосипедов");
        return "bike-details";
    }


}
