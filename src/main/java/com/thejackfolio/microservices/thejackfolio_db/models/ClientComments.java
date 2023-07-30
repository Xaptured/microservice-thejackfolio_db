/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The class type Client comments.
 */
@Component
public class ClientComments implements Serializable {

    private String email;
    private List<String> comments;

    /**
     * Instantiates a new Client comments.
     */
    public ClientComments() {
    }

    /**
     * Instantiates a new Client comments.
     *
     * @param email    the email
     * @param comments the comments
     */
    public ClientComments(String email, List<String> comments) {
        this.email = email;
        this.comments = comments;
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
     * Gets comments.
     *
     * @return the comments
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientComments that = (ClientComments) o;
        return Objects.equals(email, that.email) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, comments);
    }

    @Override
    public String toString() {
        return "ClientComments{" +
                "email='" + email + '\'' +
                ", comments=" + comments +
                '}';
    }
}
