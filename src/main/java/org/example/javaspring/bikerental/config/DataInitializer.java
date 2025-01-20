package org.example.javaspring.bikerental.config;

import org.example.javaspring.bikerental.entities.Bike;
import org.example.javaspring.bikerental.repositories.BikeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BikeRepository bikeRepository;

    public DataInitializer(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bikeRepository.count() == 0) {
            bikeRepository.save(new Bike(
                    "Городской велосипед",
                    "/images/citybike.jpg",
                    "Идеальный для прогулок по городу",
                    "Комфортный городской велосипед с удобным седлом, корзиной и 7 скоростями.",
                    150.0
            ));
            bikeRepository.save(new Bike(
                    "Горный велосипед",
                    "/images/mtb.jpg",
                    "Для приключений на пересечённой местности",
                    "Надёжный MTB с амортизаторами, дисковыми тормозами и 21-скоростной трансмиссией.",
                    200.0
            ));
            bikeRepository.save(new Bike(
                    "Шоссейный велосипед",
                    "/images/roadbike.jpg",
                    "Быстрый и легкий для длительных поездок",
                    "Высокопроизводительный шоссейный велосипед с аэродинамическим профилем и тонкими шинами.",
                    180.0
            ));
            bikeRepository.save(new Bike(
                    "Электровелосипед",
                    "/images/electricbike.jpg",
                    "С электроприводом для лёгких поездок",
                    "Удобный электровелосипед с мощным мотором и длительным временем работы аккумулятора.",
                    250.0
            ));
            System.out.println("Инициализировано тестовых велосипедов.");
        } else {
            System.out.println("В базе уже есть велосипеды. Инициализация не требуется.");
        }
    }
}
