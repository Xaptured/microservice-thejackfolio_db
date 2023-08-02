/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProfessionalDetails {

    private List<Organizations> organizations;
    private List<Projects> projects;
    private List<TechStacks> techStacks;
    private List<Roles> roles;

    public ProfessionalDetails() {
    }

    public ProfessionalDetails(List<Organizations> organizations, List<Projects> projects, List<TechStacks> techStacks, List<Roles> roles) {
        this.organizations = organizations;
        this.projects = projects;
        this.techStacks = techStacks;
        this.roles = roles;
    }

    public List<Organizations> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organizations> organizations) {
        this.organizations = organizations;
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    public List<TechStacks> getTechStacks() {
        return techStacks;
    }

    public void setTechStacks(List<TechStacks> techStacks) {
        this.techStacks = techStacks;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalDetails that = (ProfessionalDetails) o;
        return Objects.equals(organizations, that.organizations) && Objects.equals(projects, that.projects) && Objects.equals(techStacks, that.techStacks) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations, projects, techStacks, roles);
    }

    @Override
    public String toString() {
        return "ProfessionalDetails{" +
                "organizations=" + organizations +
                ", projects=" + projects +
                ", techStacks=" + techStacks +
                ", roles=" + roles +
                '}';
    }
}
