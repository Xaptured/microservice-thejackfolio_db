/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "organization_id")
    private Integer organizationId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Projects() {
    }

    public Projects(Integer id, Integer organizationId, String name, String description) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projects projects = (Projects) o;
        return Objects.equals(id, projects.id) && Objects.equals(organizationId, projects.organizationId) && Objects.equals(name, projects.name) && Objects.equals(description, projects.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationId, name, description);
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
