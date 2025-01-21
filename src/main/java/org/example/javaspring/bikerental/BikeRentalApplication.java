package org.example.javaspring.bikerental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.javaspring.bikerental.repositories")
public class BikeRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BikeRentalApplication.class, args);
    }
}