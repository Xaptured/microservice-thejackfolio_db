/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.SkillType;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * The class type Skill.
 */
@Component
public class Skill implements Serializable {

    private Map<SkillType, String> skills;

    /**
     * Instantiates a new Skill.
     */
    public Skill() {
    }

    /**
     * Instantiates a new Skill.
     *
     * @param skills the skills
     */
    public Skill(Map<SkillType, String> skills) {
        this.skills = skills;
    }

    /**
     * Gets skills.
     *
     * @return the skills
     */
    public Map<SkillType, String> getSkills() {
        return skills;
    }

    /**
     * Sets skills.
     *
     * @param skills the skills
     */
    public void setSkills(Map<SkillType, String> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skills, skill.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skills=" + skills +
                '}';
    }
}
