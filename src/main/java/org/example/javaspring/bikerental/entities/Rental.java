package org.example.javaspring.bikerental.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @Column(name = "rentalid")
    private Long rentalId;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "bicycleid")
    private Long bicycleId;

    private LocalDateTime startdate;
    private LocalDateTime enddate;

    private Double cost;

    public Long getRentalId() {
        return rentalId;
    }
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getBicycleId() {
        return bicycleId;
    }
    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }
    public LocalDateTime getStartdate() {
        return startdate;
    }
    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }
    public LocalDateTime getEnddate() {
        return enddate;
    }
    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
}
