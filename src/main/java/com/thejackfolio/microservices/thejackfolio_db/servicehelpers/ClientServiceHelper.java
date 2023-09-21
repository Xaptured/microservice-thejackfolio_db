/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.ClientCommentsRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceHelper.class);

    @Autowired
    private ClientCommentsRepository repository;

    public void saveClientComments(ClientComments comments) throws DataBaseOperationException {
        try {
            repository.save(comments);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<ClientComments> findNonRepliedComments() throws DataBaseOperationException {
        List<ClientComments> comments = null;
        try {
            comments = repository.findByNonReplied();
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return comments;
    }

    public ClientComments findCommentById(Integer commentId) throws DataBaseOperationException {
        Optional<ClientComments> comment;
        try{
            comment = repository.findById(commentId);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return comment.orElse(null);
    }
}
