/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.PersonalDetailsMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.PersonalDetailsServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalDetailsService.class);

    @Autowired
    private PersonalDetailsMapper mapper;
    @Autowired
    private PersonalDetailsServiceHelper helper;

    public void savePersonalDetails(PersonalDetails personalDetails) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.PersonalDetails entity = mapper.modelToEntity(personalDetails);
        if (entity != null) {
            if (helper.findDetailsByEmailId(entity.getEmail()) != null) {
                Integer id = helper.findIdByEmailId(entity.getEmail());
                entity.setId(id);
            }
            helper.savePersonalDetails(entity);
        }
    }

    public PersonalDetails getPersonalDetails() throws MapperException, DataBaseOperationException {
        PersonalDetails personalDetails = null;
        List<com.thejackfolio.microservices.thejackfolio_db.entities.PersonalDetails> entities = null;
        entities = helper.findAllPersonalDetails();
        personalDetails = mapper.entityToModel(entities.get(0));
        return personalDetails;
    }
}
