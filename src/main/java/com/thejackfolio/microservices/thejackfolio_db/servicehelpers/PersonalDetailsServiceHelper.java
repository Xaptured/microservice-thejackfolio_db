/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.PersonalDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.PersonalDetailsRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDetailsServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalDetailsServiceHelper.class);
    @Autowired
    private PersonalDetailsRepository repository;

    public PersonalDetails findDetailsByEmailId(String email) throws DataBaseOperationException {
        PersonalDetails personalDetails = null;
        try {
            personalDetails = repository.findByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return personalDetails;
    }

    public Integer findIdByEmailId(String email) throws DataBaseOperationException {
        Integer id = null;
        try {
            id = repository.findIdByEmailId(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return id;
    }

    public void savePersonalDetails(PersonalDetails details) throws DataBaseOperationException {
        try {
            repository.save(details);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<PersonalDetails> findAllPersonalDetails() throws DataBaseOperationException {
        List<PersonalDetails> entity = null;
        try {
            entity = repository.findAll();
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return entity;
    }
}
