/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "joiners")
public class Joiners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "description")
    private String description;
    @Column(name = "joiningDate")
    private LocalDate joiningDate;
    @Column(name = "isOnboarded")
    private boolean isOnboarded;

    public Joiners() {
    }

    public Joiners(Integer id, String name, String email, String description, LocalDate joiningDate, boolean isOnboarded) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.joiningDate = joiningDate;
        this.isOnboarded = isOnboarded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public boolean isOnboarded() {
        return isOnboarded;
    }

    public void setOnboarded(boolean onboarded) {
        isOnboarded = onboarded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joiners joiners = (Joiners) o;
        return isOnboarded == joiners.isOnboarded && Objects.equals(id, joiners.id) && Objects.equals(name, joiners.name) && Objects.equals(email, joiners.email) && Objects.equals(description, joiners.description) && Objects.equals(joiningDate, joiners.joiningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, description, joiningDate, isOnboarded);
    }

    @Override
    public String toString() {
        return "Joiners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", joiningDate=" + joiningDate +
                ", isOnboarded=" + isOnboarded +
                '}';
    }
}
