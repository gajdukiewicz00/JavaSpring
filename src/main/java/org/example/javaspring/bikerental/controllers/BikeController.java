package org.example.javaspring.bikerental.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.entities.Rental;
import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.example.javaspring.bikerental.services.BikeService;
import org.example.javaspring.bikerental.services.FileStorageService;
import org.example.javaspring.bikerental.services.RentalService;
import org.example.javaspring.bikerental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class BikeController {

    private final BikeService bikeService;
    private final RentalService rentalService;
    private final UserService userService;
    private BikeRepository bikeRepository;
    private FileStorageService fileStorageService;

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

    @PostMapping("/bikes/save")
    public String saveBike(@RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("price") double price,
                           @RequestParam(value = "file", required = false) MultipartFile file,
                           Model model) {
        try {
            // Создаём объект Bike
            Bike bike = new Bike();
            bike.setTitle(title);
            bike.setDescription(description);
            bike.setPrice(price);
            bike.setRented(false);

            // Проверяем наличие файла
            if (file != null && !file.isEmpty()) {
                // Сохраняем файл
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";
                Path filePath = Paths.get(uploadDir + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());

                bike.setImageUrl("/images/" + fileName);
            } else {
                // Устанавливаем изображение по умолчанию
                bike.setImageUrl("/images/default-bike.jpg");
            }

            // Сохраняем объект в базе данных
            bikeRepository.save(bike);

            return "redirect:/admin";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ошибка при сохранении велосипеда: " + e.getMessage());
            return "error";
        }
    }




    @GetMapping("/bikes/edit/{id}")
    public String editBike(@PathVariable Long id, Model model) {
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Bike not found"));
        model.addAttribute("bike", bike);
        return "bike-form";
    }


}
