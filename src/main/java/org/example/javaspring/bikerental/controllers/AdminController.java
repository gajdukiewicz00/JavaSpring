package org.example.javaspring.bikerental.controllers;

import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BikeRepository bikeRepository;

    public AdminController(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        List<Bike> bikes = bikeRepository.findAll();
        model.addAttribute("bikes", bikes);
        return "admin";
    }

    @GetMapping("/new")
    public String newBikeForm(Model model) {
        model.addAttribute("bike", new Bike());
        model.addAttribute("actionUrl", "/admin/new");
        return "bike-form";
    }

    @PostMapping("/new")
    public String createBike(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam(value = "rented", defaultValue = "false") boolean rented,
                             @RequestParam(value = "file", required = false) MultipartFile file) {
        Bike bike = new Bike();
        bike.setTitle(title);
        bike.setDescription(description);
        bike.setPrice(price);
        bike.setRented(rented);

        try {
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";
                Path filePath = Paths.get(uploadDir + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
                bike.setImageUrl("/images/" + fileName);
            } else {
                bike.setImageUrl("/images/default-bike.jpg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bikeRepository.save(bike);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editBike(@PathVariable Long id, Model model) {
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Bike not found"));
        model.addAttribute("bike", bike);
        model.addAttribute("actionUrl", "/admin/" + id + "/edit");
        return "bike-form";
    }

    @PostMapping("/{id}/edit")
    public String updateBike(@PathVariable Long id,
                             @RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam(value = "rented", defaultValue = "false") boolean rented,
                             @RequestParam(value = "file", required = false) MultipartFile file) {
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Bike not found"));
        bike.setTitle(title);
        bike.setDescription(description);
        bike.setPrice(price);
        bike.setRented(rented);

        try {
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";
                Path filePath = Paths.get(uploadDir + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
                bike.setImageUrl("/images/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bikeRepository.save(bike);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteBike(@PathVariable Long id) {
        bikeRepository.deleteById(id);
        return "redirect:/admin";
    }
}
