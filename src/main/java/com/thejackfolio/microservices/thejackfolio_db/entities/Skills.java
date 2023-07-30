/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The class type Skills.
 */
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

    /**
     * Instantiates a new Skills.
     */
    public Skills() {
    }

    /**
     * Instantiates a new Skills.
     *
     * @param id        the id
     * @param skillType the skill type
     * @param skill     the skill
     */
    public Skills(Integer id, String skillType, String skill) {
        this.id = id;
        this.skillType = skillType;
        this.skill = skill;
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
     * Gets skill type.
     *
     * @return the skill type
     */
    public String getSkillType() {
        return skillType;
    }

    /**
     * Sets skill type.
     *
     * @param skillType the skill type
     */
    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    /**
     * Gets skill.
     *
     * @return the skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * Sets skill.
     *
     * @param skill the skill
     */
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
