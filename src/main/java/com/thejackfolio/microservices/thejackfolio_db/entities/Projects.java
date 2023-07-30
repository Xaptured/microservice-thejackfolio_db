/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The class type Projects.
 */
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

    /**
     * Instantiates a new Projects.
     */
    public Projects() {
    }

    /**
     * Instantiates a new Projects.
     *
     * @param id             the id
     * @param organizationId the organization id
     * @param name           the name
     * @param description    the description
     */
    public Projects(Integer id, Integer organizationId, String name, String description) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets organization id.
     *
     * @return the organization id
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * Sets organization id.
     *
     * @param organizationId the organization id
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
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
