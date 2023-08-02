/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.Skills;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.SkillsMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Skill;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.SkillsServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    @Autowired
    private SkillsMapper mapper;
    @Autowired
    private SkillsServiceHelper helper;

    public void saveSkills(Skill skill) throws DataBaseOperationException, MapperException {
        List<Skills> skillEntities = mapper.modelToEntity(skill.getSkills());
        helper.saveSkills(skillEntities);
    }

    public Skill getSkills() throws DataBaseOperationException, MapperException {
        Skill skill = null;
        List<Skills> skillEntities = helper.getSkills();
        skill = mapper.entityToModel(skillEntities);
        return skill;
    }
}
