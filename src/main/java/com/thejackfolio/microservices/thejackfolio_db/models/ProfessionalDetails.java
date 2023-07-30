/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The class type Professional details.
 */
@Component
public class ProfessionalDetails implements Serializable {

    private List<Organization> organizations;
    private Map<Organization, Project> projects;

    /**
     * Instantiates a new Professional details.
     */
    public ProfessionalDetails() {
    }

    /**
     * Instantiates a new Professional details.
     *
     * @param organizations the organizations
     * @param projects      the projects
     */
    public ProfessionalDetails(List<Organization> organizations, Map<Organization, Project> projects) {
        this.organizations = organizations;
        this.projects = projects;
    }

    /**
     * Gets organizations.
     *
     * @return the organizations
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }

    /**
     * Sets organizations.
     *
     * @param organizations the organizations
     */
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    /**
     * Gets projects.
     *
     * @return the projects
     */
    public Map<Organization, Project> getProjects() {
        return projects;
    }

    /**
     * Sets projects.
     *
     * @param projects the projects
     */
    public void setProjects(Map<Organization, Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalDetails that = (ProfessionalDetails) o;
        return Objects.equals(organizations, that.organizations) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations, projects);
    }

    @Override
    public String toString() {
        return "ProfessionalDetails{" +
                "organizations=" + organizations +
                ", projects=" + projects +
                '}';
    }
}
