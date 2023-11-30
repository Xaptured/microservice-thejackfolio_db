/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "leaderboard")
public class Leaderboards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "event_id")
    private Integer eventId;
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "doc_name")
    private String documentName;
    @Column(name = "doc_type")
    private String documentType;
    @Column(name = "doc_path")
    private String documentPath;

    public Leaderboards() {
    }

    public Leaderboards(Integer id, Integer eventId, Integer teamId, String documentName, String documentType, String documentPath) {
        this.id = id;
        this.eventId = eventId;
        this.teamId = teamId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentPath = documentPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaderboards that = (Leaderboards) o;
        return Objects.equals(id, that.id) && Objects.equals(eventId, that.eventId) && Objects.equals(teamId, that.teamId) && Objects.equals(documentName, that.documentName) && Objects.equals(documentType, that.documentType) && Objects.equals(documentPath, that.documentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, teamId, documentName, documentType, documentPath);
    }

    @Override
    public String toString() {
        return "Leaderboards{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", teamId=" + teamId +
                ", documentName='" + documentName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentPath='" + documentPath + '\'' +
                '}';
    }
}
