package org.example.javaspring.bikerental.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean rented;
    private double price;
    private String imageUrl;

    public Bike() {
    }

    public Bike(String title, String description, boolean rented, double price) {
        this.title = title;
        this.description = description;
        this.rented = rented;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isRented() { return rented; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setRented(boolean rented) { this.rented = rented; }
    public void setPrice(double price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rented=" + rented +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
