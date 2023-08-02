/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.ClientsMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.ClientServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientsMapper mapper;
    @Autowired
    private ClientServiceHelper helper;

    public void saveComments(ClientComments clientComments) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = mapper.modelToEntityComments(clientComments);
        helper.saveClientComments(commentEntity);
    }

    public List<ClientComments> getComments() throws MapperException, DataBaseOperationException {
        List<ClientComments> comments = null;
        List<com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments> commentEntities = helper.findNonRepliedComments();
        comments = mapper.entityToModel(commentEntities);
        return comments;
    }
}
