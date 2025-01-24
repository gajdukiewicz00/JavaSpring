package org.example.javaspring.bikerental.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.services.BikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BikeService bikeService;

    public AdminController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return "ADMIN".equals(role);
    }

    @GetMapping
    public String adminPage(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("bikes", bikeService.findAll());
        return "admin";
    }

    @GetMapping("/new")
    public String newBikeForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        Bike bike = new Bike();
        model.addAttribute("bike", bike);
        model.addAttribute("actionUrl", "/admin/new");
        return "bike-form";
    }

    @PostMapping("/new")
    public String createBike(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            HttpSession session
    ) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        Bike bike = new Bike(title, description, false, price);
        bikeService.save(bike);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editBikeForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        Bike bike = bikeService.findById(id);
        if (bike == null) {
            return "redirect:/admin";
        }
        model.addAttribute("bike", bike);
        String editUrl = "/admin/" + id + "/edit";
        model.addAttribute("actionUrl", editUrl);

        return "bike-form";
    }

    @PostMapping("/{id}/edit")
    public String updateBike(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam(defaultValue = "false") boolean rented,
            HttpSession session
    ) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        Bike bike = bikeService.findById(id);
        if (bike == null) {
            return "redirect:/admin";
        }
        bike.setTitle(title);
        bike.setDescription(description);
        bike.setPrice(price);
        bike.setRented(rented);

        bikeService.save(bike);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteBike(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        bikeService.deleteById(id);
        return "redirect:/admin";
    }
}
