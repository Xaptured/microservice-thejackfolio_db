/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.InstagramTokenRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstagramTokenServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstagramTokenServiceHelper.class);
    @Autowired
    private InstagramTokenRepository repository;

    public void saveInstagramToken(Instagram_Token token) throws DataBaseOperationException {
        try{
            repository.save(token);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<Instagram_Token> findAllInstagramToken() throws DataBaseOperationException {
        List<Instagram_Token> entities = null;
        try{
            entities = repository.findAll();
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return entities;
    }
}
