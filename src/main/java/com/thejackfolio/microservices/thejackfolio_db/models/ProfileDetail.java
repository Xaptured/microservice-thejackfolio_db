/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ProfileDetail {
    private String name;
    private String phone;
    private String city;
    private String email;
    private Game[] games;
    private String message;

    public ProfileDetail() {
    }

    public ProfileDetail(String name, String phone, String city, String email, Game[] games, String message) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.games = games;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDetail detail = (ProfileDetail) o;
        return Objects.equals(name, detail.name) && Objects.equals(phone, detail.phone) && Objects.equals(city, detail.city) && Objects.equals(email, detail.email) && Arrays.equals(games, detail.games) && Objects.equals(message, detail.message);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, phone, city, email, message);
        result = 31 * result + Arrays.hashCode(games);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileDetail{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", games=" + Arrays.toString(games) +
                ", message='" + message + '\'' +
                '}';
    }
}
