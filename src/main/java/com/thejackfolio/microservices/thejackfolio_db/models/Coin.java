/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Coin {

    private String email;
    private Integer balance;
    private String message;

    public Coin() {
    }

    public Coin(String email, Integer balance, String message) {
        this.email = email;
        this.balance = balance;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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
        Coin coin = (Coin) o;
        return Objects.equals(email, coin.email) && Objects.equals(balance, coin.balance) && Objects.equals(message, coin.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, balance, message);
    }

    @Override
    public String toString() {
        return "Coin{" +
                "email='" + email + '\'' +
                ", balance=" + balance +
                ", message='" + message + '\'' +
                '}';
    }
}
