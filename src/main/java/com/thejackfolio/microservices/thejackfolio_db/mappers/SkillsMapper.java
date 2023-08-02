/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Skills;
import com.thejackfolio.microservices.thejackfolio_db.enums.SkillType;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Skill;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkillsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillsMapper.class);

    public List<Skills> modelToEntity(Map<SkillType, List<String>> skills) throws MapperException {
        List<Skills> skillEntities = null;
        try {
            if (skills != null && !skills.isEmpty()) {
                skillEntities = new ArrayList<>();
                List<String> personalSkills = skills.get(SkillType.PERSONAL);
                List<String> professionalSkills = skills.get(SkillType.PROFESSIONAL);
                for (String personalSkill : personalSkills) {
                    Skills skillEntity = new Skills();
                    skillEntity.setSkillType(SkillType.PERSONAL.name());
                    skillEntity.setSkill(personalSkill);
                    skillEntities.add(skillEntity);
                }
                for (String professionalSkill : professionalSkills) {
                    Skills skillEntity = new Skills();
                    skillEntity.setSkillType(SkillType.PROFESSIONAL.name());
                    skillEntity.setSkill(professionalSkill);
                    skillEntities.add(skillEntity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
        }
        return skillEntities;
    }

    public Skill entityToModel(List<Skills> skills) throws MapperException {
        Skill skill = null;
        try {
            if (skills != null) {
                skill = new Skill();
                Map<SkillType, List<String>> skillMap = new HashMap<>();
                List<String> personalSkills = new ArrayList<>();
                List<String> professionalSkills = new ArrayList<>();
                for (Skills skillEntity : skills) {
                    if (skillEntity.getSkillType().equals(SkillType.PERSONAL.name())) {
                        personalSkills.add(skillEntity.getSkill());
                    } else if (skillEntity.getSkillType().equals(SkillType.PROFESSIONAL.name())) {
                        professionalSkills.add(skillEntity.getSkill());
                    }
                }
                skillMap.put(SkillType.PERSONAL, personalSkills);
                skillMap.put(SkillType.PROFESSIONAL, professionalSkills);
                skill.setSkills(skillMap);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return skill;
    }
}
