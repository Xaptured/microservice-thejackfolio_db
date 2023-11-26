/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "viewers")
public class Viewers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "event_id")
    private Integer eventId;

    public Viewers() {
    }

    public Viewers(Integer id, String email, Integer eventId) {
        this.id = id;
        this.email = email;
        this.eventId = eventId;
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viewers viewers = (Viewers) o;
        return Objects.equals(id, viewers.id) && Objects.equals(email, viewers.email) && Objects.equals(eventId, viewers.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, eventId);
    }

    @Override
    public String toString() {
        return "Viewers{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
