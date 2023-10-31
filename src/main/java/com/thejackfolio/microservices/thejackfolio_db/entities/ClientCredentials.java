/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.Role;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "client_credential")
public class ClientCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;
    @Column(name = "verified")
    private boolean verified;
    @Column(name = "code")
    private Integer code;

    public ClientCredentials() {
    }

    public ClientCredentials(int id, String email, String password, Role role, boolean verified, Integer code) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.verified = verified;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCredentials that = (ClientCredentials) o;
        return id == that.id && verified == that.verified && Objects.equals(email, that.email) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role, verified);
    }

    @Override
    public String toString() {
        return "ClientCredentials{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", verified=" + verified +
                '}';
    }
}
