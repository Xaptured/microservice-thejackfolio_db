/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "client_comments")
public class ClientComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "comment")
    private String comment;
    @Column(name = "replied")
    private boolean replied;

    public ClientComments() {
    }

    public ClientComments(Integer id, String email, String comment, boolean replied) {
        this.id = id;
        this.email = email;
        this.comment = comment;
        this.replied = replied;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isReplied() {
        return replied;
    }

    public void setReplied(boolean replied) {
        this.replied = replied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientComments that = (ClientComments) o;
        return replied == that.replied && Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, comment, replied);
    }

    @Override
    public String toString() {
        return "ClientComments{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", replied=" + replied +
                '}';
    }
}
