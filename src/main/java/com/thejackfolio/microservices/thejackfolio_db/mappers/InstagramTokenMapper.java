/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.utilities.EncryptDecrypt;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstagramTokenMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstagramTokenMapper.class);
    @Autowired
    private EncryptDecrypt encryptDecrypt;

    public Instagram_Token modelToEntity(Instagram_Token entity, com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token model) throws MapperException {
        try{
            if(entity != null && model != null){
                entity.setToken(encryptDecrypt.encrypt(model.getToken()));
                entity.setCreationDate(model.getCreationDate());
                entity.setExpirationDate(model.getExpirationDate());
            }
        } catch (Exception exception){
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return entity;
    }

    public Instagram_Token modelToEntity(com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token model) throws MapperException {
        Instagram_Token entity = null;
        try{
            if(model != null){
                entity = new Instagram_Token();
                entity.setToken(encryptDecrypt.encrypt(model.getToken()));
                entity.setCreationDate(model.getCreationDate());
                entity.setExpirationDate(model.getExpirationDate());
            }
        } catch (Exception exception){
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return entity;
    }

    public com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token entityToModel(Instagram_Token entity) throws MapperException {
        com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token model = null;
        try{
            if(entity != null){
                model = new com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token();
                model.setToken(encryptDecrypt.decrypt(entity.getToken()));
                model.setCreationDate(entity.getCreationDate());
                model.setExpirationDate(entity.getExpirationDate());
            }
        } catch (Exception exception){
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return model;
    }
}
