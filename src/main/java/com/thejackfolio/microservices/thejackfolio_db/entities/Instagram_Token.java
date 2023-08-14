/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "instagram_token")
public class Instagram_Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "expiration_date")
    private Date expirationDate;

    public Instagram_Token() {
    }

    public Instagram_Token(Integer id, String token, Date creationDate, Date expirationDate) {
        this.id = id;
        this.token = token;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instagram_Token that = (Instagram_Token) o;
        return Objects.equals(id, that.id) && Objects.equals(token, that.token) && Objects.equals(creationDate, that.creationDate) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, creationDate, expirationDate);
    }

    @Override
    public String toString() {
        return "Instagram_Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
