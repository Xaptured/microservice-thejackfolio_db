/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "skill_type")
    private String skillType;
    @Column(name = "skill")
    private String skill;

    public Skills() {
    }

    public Skills(Integer id, String skillType, String skill) {
        this.id = id;
        this.skillType = skillType;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return Objects.equals(id, skills.id) && Objects.equals(skillType, skills.skillType) && Objects.equals(skill, skills.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillType, skill);
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", skillType='" + skillType + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
