/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "role")
    private String role;

    public Roles() {
    }

    public Roles(Integer id, Integer projectId, String role) {
        this.id = id;
        this.projectId = projectId;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(id, roles.id) && Objects.equals(projectId, roles.projectId) && Objects.equals(role, roles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, role);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", role='" + role + '\'' +
                '}';
    }
}
