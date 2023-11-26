/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "team_details")
public class TeamDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "player_number")
    private Integer playerNumber;

    public TeamDetails() {
    }

    public TeamDetails(Integer id, String email, Integer teamId, Integer playerNumber) {
        this.id = id;
        this.email = email;
        this.teamId = teamId;
        this.playerNumber = playerNumber;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDetails that = (TeamDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(teamId, that.teamId) && Objects.equals(playerNumber, that.playerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, teamId, playerNumber);
    }

    @Override
    public String toString() {
        return "TeamDetails{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", teamId=" + teamId +
                ", playerNumber=" + playerNumber +
                '}';
    }
}
