/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalDetailsMapper.class);

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
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
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
                    commentModel.setMessage(StringConstants.REQUEST_PROCESSED);
                    commentModels.add(commentModel);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return commentModels;
    }
}
