/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.TeamStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "event_id")
    private Integer eventId;
    @Column(name = "status")
    private TeamStatus teamStatus;

    public Teams() {
    }

    public Teams(Integer id, String name, Integer eventId, TeamStatus teamStatus) {
        this.id = id;
        this.name = name;
        this.eventId = eventId;
        this.teamStatus = teamStatus;
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return Objects.equals(id, teams.id) && Objects.equals(name, teams.name) && Objects.equals(eventId, teams.eventId) && teamStatus == teams.teamStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, eventId, teamStatus);
    }

    @Override
    public String toString() {
        return "Teams{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventId=" + eventId +
                ", teamStatus=" + teamStatus +
                '}';
    }
}
