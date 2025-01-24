package org.example.javaspring.bikerental.services;

import org.example.javaspring.bikerental.entities.User;
import org.example.javaspring.bikerental.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String password, String role) {
        if (userRepository.findByEmail(email) != null) {
            return null; // в реальном проекте можно бросать исключение
        }
        User user = new User(name, email, password, role);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
