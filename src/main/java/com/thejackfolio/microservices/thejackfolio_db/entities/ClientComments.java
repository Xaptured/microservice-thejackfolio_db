/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The class type Client comments.
 */
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

    /**
     * Instantiates a new Client comments.
     */
    public ClientComments() {
    }

    /**
     * Instantiates a new Client comments.
     *
     * @param id      the id
     * @param email   the email
     * @param comment the comment
     */
    public ClientComments(Integer id, String email, String comment) {
        this.id = id;
        this.email = email;
        this.comment = comment;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientComments that = (ClientComments) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, comment);
    }

    @Override
    public String toString() {
        return "ClientComments{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
