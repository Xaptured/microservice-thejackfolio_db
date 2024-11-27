/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamMateEntity;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANDataBaseException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.ResourceNotFoundException;
import com.thejackfolio.microservices.thejackfolio_db.models.LANTeam;
import com.thejackfolio.microservices.thejackfolio_db.repositories.ClientCredentialsRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.LANEventRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.LANTeamMateRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.LANTeamRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LANEventServiceHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventServiceHelper.class);

    @Autowired
    private LANEventRepository lanEventRepository;
    @Autowired
    private LANTeamRepository lanTeamRepository;
    @Autowired
    private LANTeamMateRepository lanTeamMateRepository;
    @Autowired
    private ClientCredentialsRepository clientCredentialsRepository;

    public LANEvent saveLANEvent(LANEvent event) {
        try {
            return lanEventRepository.save(event);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public LANEvent findLANEventByName(String name) {
        try {
            return lanEventRepository.findByName(name).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEmailExist(String email) {
        try {
            return lanEventRepository.existsByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchFutureEventsWRTEmail(String email) {
        try {
            return lanEventRepository.fetchFutureEventsWRTEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchPastEventsWRTEmail(String email) {
        try {
            return lanEventRepository.fetchPastEventsWRTEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public LANTeamEntity saveTeam(LANTeamEntity team) {
        try {
            return lanTeamRepository.save(team);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveTeamMates(List<LANTeamMateEntity> teamMates) {
        try {
            lanTeamMateRepository.saveAll(teamMates);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEmailExistInClientCredentials(String email) {
        try {
            return clientCredentialsRepository.existsByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANTeamMateEntity> checkEmailIsRegistered(List<LANTeamMateEntity> teamMateEntities) {
        for (LANTeamMateEntity teamMateEntity : teamMateEntities) {
            boolean isRegistered = isEmailExistInClientCredentials(teamMateEntity.getEmail());
            teamMateEntity.setEmailRegistered(isRegistered);
        }
        return teamMateEntities;
    }
}
