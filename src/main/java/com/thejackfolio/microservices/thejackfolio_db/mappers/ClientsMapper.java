/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.entities.ClientCredentials;
import com.thejackfolio.microservices.thejackfolio_db.entities.ProfileDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.ClientCredential;
import com.thejackfolio.microservices.thejackfolio_db.models.ProfileDetail;
import com.thejackfolio.microservices.thejackfolio_db.utilities.EncryptDecrypt;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsMapper.class);

    @Autowired
    private EncryptDecrypt encryptDecrypt;

    public ClientComments modelToEntityComments(com.thejackfolio.microservices.thejackfolio_db.models.ClientComments comments) throws MapperException {
        ClientComments commentEntity = null;
        try {
            if (comments != null) {
                commentEntity = new ClientComments();
                commentEntity.setEmail(comments.getEmail());
                commentEntity.setComment(comments.getComments());
                commentEntity.setReplied(comments.isReplied());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }

        return commentEntity;
    }

    public ClientComments modelToEntityComments(com.thejackfolio.microservices.thejackfolio_db.models.ClientComments comments, Integer commentId) throws MapperException {
        ClientComments commentEntity = null;
        try {
            if(comments != null) {
                commentEntity = new ClientComments();
                commentEntity.setEmail(comments.getEmail());
                commentEntity.setComment(comments.getComments());
                commentEntity.setReplied(comments.isReplied());
                commentEntity.setId(commentId);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }

        return commentEntity;
    }

    public List<com.thejackfolio.microservices.thejackfolio_db.models.ClientComments> entityToModel(List<ClientComments> comments) throws MapperException {
        List<com.thejackfolio.microservices.thejackfolio_db.models.ClientComments> commentModels = null;
        try {
            if (comments != null && !comments.isEmpty()) {
                commentModels = new ArrayList<>();
                for (ClientComments comment : comments) {
                    com.thejackfolio.microservices.thejackfolio_db.models.ClientComments commentModel =
                            new com.thejackfolio.microservices.thejackfolio_db.models.ClientComments();
                    commentModel.setEmail(comment.getEmail());
                    commentModel.setComments(comment.getComment());
                    commentModel.setReplied(comment.isReplied());
                    commentModel.setMessage(comment.getId().toString());
                    commentModels.add(commentModel);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return commentModels;
    }

    public com.thejackfolio.microservices.thejackfolio_db.models.ClientComments entityToModelForComment(ClientComments commentEntity) throws MapperException {
        com.thejackfolio.microservices.thejackfolio_db.models.ClientComments commentModel = null;
        try{
            if(commentEntity != null)
            {
                commentModel = new com.thejackfolio.microservices.thejackfolio_db.models.ClientComments();
                commentModel.setEmail(commentEntity.getEmail());
                commentModel.setComments(commentEntity.getComment());
                commentModel.setReplied(commentEntity.isReplied());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return commentModel;
    }

    public ClientCredentials modelToEntityCredential(ClientCredential credential) throws MapperException {
        ClientCredentials credentialEntity = null;
        try{
            if(credential != null) {
                credentialEntity = new ClientCredentials();
                credentialEntity.setEmail(credential.getEmail());
                credentialEntity.setPassword(credential.getPassword());
                credentialEntity.setRole(credential.getRole());
                credentialEntity.setVerified(credentialEntity.isVerified());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return credentialEntity;
    }

    public ClientCredential entityToModelCredential(ClientCredentials credentials) throws MapperException {
        ClientCredential credentialModel = null;
        try {
            if(credentials != null) {
                credentialModel = new ClientCredential();
                credentialModel.setEmail(credentials.getEmail());
                credentialModel.setPassword(credentials.getPassword());
                credentialModel.setRole(credentials.getRole());
                credentialModel.setVerified(credentials.isVerified());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return credentialModel;
    }

    public ProfileDetails modelToEntityDetails(ProfileDetail detailModel) throws MapperException {
        ProfileDetails detailsEntity = null;
        try {
            if(detailModel != null) {
                detailsEntity = new ProfileDetails();
                detailsEntity.setName(detailModel.getName());
                detailsEntity.setEmail(detailModel.getEmail());
                detailsEntity.setPhone(detailModel.getPhone());
                detailsEntity.setCity(detailModel.getCity());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }

        return detailsEntity;
    }

}
