/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class Clients implements Serializable {

    private String email;
    private String password;
    private boolean verified;
    private ClientDetails clientDetails;

    public Clients() {
    }

    public Clients(String email, String password, boolean verified, ClientDetails clientDetails) {
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.clientDetails = clientDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return verified == clients.verified && Objects.equals(email, clients.email) && Objects.equals(password, clients.password) && Objects.equals(clientDetails, clients.clientDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, verified, clientDetails);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verified=" + verified +
                ", clientDetails=" + clientDetails +
                '}';
    }
}
