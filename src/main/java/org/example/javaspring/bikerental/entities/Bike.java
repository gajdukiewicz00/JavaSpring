package org.example.javaspring.bikerental.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String shortDesc;
    private String fullDesc;
    private Double pricePerHour;

    public Bike() {}
    public Bike(String name, String imageUrl, String shortDesc, String fullDesc, Double pricePerHour) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.pricePerHour = pricePerHour;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getShortDesc() { return shortDesc; }
    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }

    public String getFullDesc() { return fullDesc; }
    public void setFullDesc(String fullDesc) { this.fullDesc = fullDesc; }

    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }
}
