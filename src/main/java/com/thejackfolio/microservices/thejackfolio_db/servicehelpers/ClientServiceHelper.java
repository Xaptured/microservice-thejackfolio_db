/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.models.EmailValidationDetails;
import com.thejackfolio.microservices.thejackfolio_db.repositories.*;
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
    @Autowired
    private ClientCredentialsRepository credentialsRepository;
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;
    @Autowired
    private PartnersRepository partnersRepository;
    @Autowired
    private JoinersRepository joinersRepository;

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

    public void saveCredentials(ClientCredentials credentials) throws DataBaseOperationException {
        try {
            credentialsRepository.save(credentials);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public ClientCredentials findCredentialByEmail(String email) throws DataBaseOperationException {
        Optional<ClientCredentials> credentialEntity = null;
        try {
            credentialEntity = credentialsRepository.findByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return credentialEntity.orElse(null);
    }

    public void verifyClientAccount(Integer clientId) throws DataBaseOperationException {
        try {
            credentialsRepository.verifyAccount(clientId);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveSecretCode(EmailValidationDetails details) throws DataBaseOperationException {
        try {
            ClientCredentials credentials = credentialsRepository.findById(details.getClientId()).orElse(null);
            if(credentials != null) {
                credentials.setCode(details.getSecretCode());
                credentialsRepository.save(credentials);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public EmailValidationDetails findDetailsById(Integer id) throws DataBaseOperationException {
        EmailValidationDetails details = null;
        try{
            ClientCredentials credentials = credentialsRepository.findById(id).orElse(null);
            if(credentials != null) {
                details = new EmailValidationDetails();
                details.setClientId(credentials.getId());
                details.setSecretCode(credentials.getCode());
            }
        } catch (Exception exception ) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return details;
    }

    public void saveProfile(ProfileDetails details) throws DataBaseOperationException {
        try {
            ProfileDetails detailEntity = profileDetailsRepository.findByEmail(details.getEmail()).orElse(null);
            if(detailEntity == null) {
                profileDetailsRepository.save(details);
            } else {
                details.setId(detailEntity.getId());
                profileDetailsRepository.save(details);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public ProfileDetails findDetailsByEmail(String email) throws DataBaseOperationException {
        ProfileDetails details = null;
        try {
            details = profileDetailsRepository.findByEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return details;
    }

    public void savePartner(Partners partner) throws DataBaseOperationException {
        try {
            partnersRepository.save(partner);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public Partners findPartnerByEmail(String email) throws DataBaseOperationException {
        Partners partner = null;
        try {
            partner = partnersRepository.findByEmail(email).orElse(null);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return partner;
    }

    public void saveJoiner(Joiners joiners) throws DataBaseOperationException {
        try {
            joinersRepository.save(joiners);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public Joiners findJoiner(String email) throws DataBaseOperationException {
        Joiners joinerEntity = null;
        try {
            joinerEntity = joinersRepository.findByEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return joinerEntity;
    }
}
