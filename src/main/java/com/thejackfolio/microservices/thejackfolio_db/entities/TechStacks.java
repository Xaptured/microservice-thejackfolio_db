/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tech_stacks")
public class TechStacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "tech_stack")
    private String techStack;

    public TechStacks() {
    }

    public TechStacks(Integer id, Integer projectId, String techStack) {
        this.id = id;
        this.projectId = projectId;
        this.techStack = techStack;
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

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechStacks that = (TechStacks) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId) && Objects.equals(techStack, that.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, techStack);
    }

    @Override
    public String toString() {
        return "TechStacks{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", techStack='" + techStack + '\'' +
                '}';
    }
}
