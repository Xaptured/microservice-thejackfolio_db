/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "interested_games")
public class InterestedGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "game_name")
    private String gameName;
    @Column(name = "game_number")
    private Integer gameNumber;

    public InterestedGames() {
    }

    public InterestedGames(int id, String email, String gameName, Integer gameNumber) {
        this.id = id;
        this.email = email;
        this.gameName = gameName;
        this.gameNumber = gameNumber;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestedGames that = (InterestedGames) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(gameName, that.gameName) && Objects.equals(gameNumber, that.gameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, gameName, gameNumber);
    }

    @Override
    public String toString() {
        return "InterestedGames{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameNumber=" + gameNumber +
                '}';
    }
}
