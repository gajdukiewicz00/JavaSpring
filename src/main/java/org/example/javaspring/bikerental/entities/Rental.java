package org.example.javaspring.bikerental.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Rental() {
    }

    public Rental(User user, Bike bike, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.bike = bike;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Bike getBike() { return bike; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setBike(Bike bike) { this.bike = bike; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
}
