package org.example.javaspring.bikerental.services;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.javaspring.bikerental.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Загружаем пользователя из базы данных
        org.example.javaspring.bikerental.entities.User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        // Создаём UserDetails для Spring Security
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword()) // Должен быть хешированный пароль
                .roles(user.getRole())       // Роль: "USER", "ADMIN"
                .build();
    }
}
