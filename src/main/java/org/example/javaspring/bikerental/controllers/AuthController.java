package org.example.javaspring.bikerental.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        User user = userService.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Неверный email или пароль");
            return "login";
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("userRole", user.getRole());
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model
    ) {
        User newUser = userService.registerUser(name, email, password, role);
        if (newUser == null) {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            return "register";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
