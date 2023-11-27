/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.ClientCredentials;
import com.thejackfolio.microservices.thejackfolio_db.entities.InterestedGames;
import com.thejackfolio.microservices.thejackfolio_db.entities.ProfileDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.ClientsMapper;
import com.thejackfolio.microservices.thejackfolio_db.mappers.GameMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.ClientServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.GameServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientsMapper mapper;
    @Autowired
    private ClientServiceHelper helper;
    @Autowired
    private GameService service;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private GameServiceHelper gameServiceHelper;

    public void saveComments(ClientComments clientComments) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = mapper.modelToEntityComments(clientComments);
        helper.saveClientComments(commentEntity);
    }

    public void updateComments(ClientComments clientComments, Integer commentId) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = mapper.modelToEntityComments(clientComments, commentId);
        helper.saveClientComments(commentEntity);
    }

    public List<ClientComments> getComments() throws MapperException, DataBaseOperationException {
        List<ClientComments> comments = null;
        List<com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments> commentEntities = helper.findNonRepliedComments();
        comments = mapper.entityToModel(commentEntities);
        return comments;
    }

    public ClientComments findCommentById(Integer commentId) throws DataBaseOperationException, MapperException {
        ClientComments comment = null;
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = helper.findCommentById(commentId);
        comment = mapper.entityToModelForComment(commentEntity);
        return comment;
    }

    public void saveCredentials(ClientCredential credential) throws MapperException, DataBaseOperationException {
        ClientCredentials credentialEntity = mapper.modelToEntityCredential(credential);
        helper.saveCredentials(credentialEntity);
        ProfileDetail detail = new ProfileDetail();
        detail.setEmail(credential.getEmail());
        saveOrUpdateProfile(detail);
    }

    public ClientCredential findClientCredential(String email) throws DataBaseOperationException, MapperException {
        ClientCredential credential = null;
        ClientCredentials credentialEntity = helper.findCredentialByEmail(email);
        credential = mapper.entityToModelCredential(credentialEntity);
        return credential;
    }

    public EmailValidationDetails findClientId(String email) throws DataBaseOperationException, MapperException {
        EmailValidationDetails details = new EmailValidationDetails();
        ClientCredentials credentialEntity = helper.findCredentialByEmail(email);
        details.setClientId(credentialEntity.getId());
        details.setSecretCode(credentialEntity.getCode());
        return details;
    }

    public void verifyClientAccount(Integer clientId) throws DataBaseOperationException {
        helper.verifyClientAccount(clientId);
    }

    public void saveSecretCode(EmailValidationDetails details) throws DataBaseOperationException {
        helper.saveSecretCode(details);
    }

    public EmailValidationDetails findDetailsById(Integer id) throws DataBaseOperationException {
        return helper.findDetailsById(id);
    }

    public ProfileDetail saveOrUpdateProfile(ProfileDetail detail) throws MapperException, DataBaseOperationException {
        ProfileDetails details = helper.findDetailsByEmail(detail.getEmail());
        if(details == null) {
            details = mapper.modelToEntityDetails(detail);
            helper.saveProfile(details);
            List<InterestedGames> interestedGames = service.saveInterestedGames(detail);
            List<InterestedGame> games = gameMapper.entityToModelGames(interestedGames);
            detail.setInterestedGames(games);
        } else {
            details = mapper.modelToEntityDetails(detail, details);
            helper.saveProfile(details);
            List<InterestedGames> interestedGames = service.updateInterestedGames(detail);
            List<InterestedGame> games = gameMapper.entityToModelGames(interestedGames);
            detail.setInterestedGames(games);
        }
        return detail;
    }

    public ProfileDetail getProfileDetails(String email) throws DataBaseOperationException, MapperException {
        ProfileDetail detail = null;
        ProfileDetails details = helper.findDetailsByEmail(email);
        if(details != null) {
            List<InterestedGames> games = gameServiceHelper.findInterestedGamesByEmail(details.getEmail());
            detail = mapper.entityToModelDetails(details, games);
        }
        return detail;
    }
}
