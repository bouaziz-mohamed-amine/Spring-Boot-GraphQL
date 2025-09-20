package com.example.graph.entity;

import com.example.graph.enums.ROLE;
import jakarta.persistence.*;

@Entity
@Table(name = "users") // database table name
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // should be stored as encrypted (BCrypt)

    @Enumerated(EnumType.STRING) // store enum as string (e.g., "ADMIN", "USER")
    @Column(nullable = false)
    private ROLE role;

    // Constructors
    public UserEntity() {
    }

    public UserEntity(String username, String password, ROLE role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }


}