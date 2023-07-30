/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.TechStack;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The class type Project.
 */
@Component
public class Project implements Serializable {

    private String name;
    private String description;
    private List<TechStack> techStacks;
    private List<String> roles;

    /**
     * Instantiates a new Project.
     */
    public Project() {
    }

    /**
     * Instantiates a new Project.
     *
     * @param name        the name
     * @param description the description
     * @param techStacks  the tech stacks
     * @param roles       the roles
     */
    public Project(String name, String description, List<TechStack> techStacks, List<String> roles) {
        this.name = name;
        this.description = description;
        this.techStacks = techStacks;
        this.roles = roles;
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

    /**
     * Gets tech stacks.
     *
     * @return the tech stacks
     */
    public List<TechStack> getTechStacks() {
        return techStacks;
    }

    /**
     * Sets tech stacks.
     *
     * @param techStacks the tech stacks
     */
    public void setTechStacks(List<TechStack> techStacks) {
        this.techStacks = techStacks;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(techStacks, project.techStacks) && Objects.equals(roles, project.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, techStacks, roles);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", techStacks=" + techStacks +
                ", roles=" + roles +
                '}';
    }
}
