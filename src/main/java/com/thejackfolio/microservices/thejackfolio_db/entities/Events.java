/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "game_id")
    private Integer gameId;
    @Column(name = "status")
    private EventStatus status;

    public Events() {
    }

    public Events(Integer id, String name, String email, EventStatus status, Integer gameId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.gameId = gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return Objects.equals(id, events.id) && Objects.equals(name, events.name) && Objects.equals(email, events.email) && Objects.equals(gameId, events.gameId) && status == events.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, gameId, status);
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gameId=" + gameId +
                ", status=" + status +
                '}';
    }
}
