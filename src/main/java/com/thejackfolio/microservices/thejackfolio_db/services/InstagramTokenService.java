/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.InstagramTokenMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.InstagramTokenServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstagramTokenService {

    @Autowired
    private InstagramTokenMapper mapper;
    @Autowired
    private InstagramTokenServiceHelper helper;

    public void saveInstagramToken(Instagram_Token instagramToken) throws DataBaseOperationException, MapperException {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token> tokens = helper.findAllInstagramToken();
        if(tokens != null && tokens.size() == 1){
            com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token tokenEntity = tokens.get(0);
            tokenEntity = mapper.modelToEntity(tokenEntity, instagramToken);
            helper.saveInstagramToken(tokenEntity);
        } else if(tokens != null && tokens.size() == 0){
            com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token token = mapper.modelToEntity(instagramToken);
            helper.saveInstagramToken(token);
        }
    }

    public Instagram_Token getInstagramToken() throws DataBaseOperationException, MapperException {
        Instagram_Token model = null;
        List<com.thejackfolio.microservices.thejackfolio_db.entities.Instagram_Token> tokens = helper.findAllInstagramToken();
        if(tokens != null && tokens.size() == 1){
            model = mapper.entityToModel(tokens.get(0));
        } else if(tokens != null && tokens.size() == 0){
            model = new Instagram_Token();
        }
        return model;
    }
}
