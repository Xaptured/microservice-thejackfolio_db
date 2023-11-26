/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "coins")
public class Coins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "balance")
    private Integer balance;

    public Coins() {
    }

    public Coins(Integer id, String email, Integer balance) {
        this.id = id;
        this.email = email;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coins coins = (Coins) o;
        return Objects.equals(id, coins.id) && Objects.equals(email, coins.email) && Objects.equals(balance, coins.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, balance);
    }

    @Override
    public String toString() {
        return "Coins{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
