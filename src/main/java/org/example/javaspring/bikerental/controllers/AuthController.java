package org.example.javaspring.bikerental.controllers;

import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/do-register")
    public String doRegister(@ModelAttribute("userForm") User userForm) {
        String encodedPass = passwordEncoder.encode(userForm.getPassword());
        userForm.setPassword(encodedPass);

        userRepository.save(userForm);
        return "redirect:/login";
    }

    @GetMapping("/logout-confirm")
    public String logoutConfirm() {
        return "logout";
    }
}
