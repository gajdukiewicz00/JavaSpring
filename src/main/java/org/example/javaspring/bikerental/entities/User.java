package org.example.javaspring.bikerental.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String contactInfo;

    @Column
    private String status;

    public User() {}

    public User(String name, String password, String contactInfo, String status) {
        this.name = name;
        this.password = password;
        this.contactInfo = contactInfo;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
