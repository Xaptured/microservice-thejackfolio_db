/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamMateEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public List<TeamWithTeamMate> fetchTeamWithTeamMate(String email) {
        try {
            List<TeamWithTeamMate> teamWithTeamMates = new ArrayList<>();
            List<Map<String, Object>> results = lanTeamRepository.fetchTeamWithTeamMate(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    TeamWithTeamMate teamWithTeamMate = new TeamWithTeamMate();
                    teamWithTeamMate.setName(result.get("NAME").toString());
                    teamWithTeamMate.setEventName(result.get("EVENT_NAME").toString());
                    teamWithTeamMate.setEmail(result.get("EMAIL").toString());
                    teamWithTeamMate.setEmailRegistered((Boolean) result.get("EMAIL_REGISTERED"));
                    teamWithTeamMate.setStatus(LANTeamStatus.fromValue(Integer.parseInt(result.get("STATUS").toString())));
                    teamWithTeamMates.add(teamWithTeamMate);
                }
            }
            return teamWithTeamMates;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        try {
            Map<String, Object> result = lanTeamRepository.fetchTeamWithTeamMateAndEventName(email, eventName).orElse(null);
            if (result != null && result.get("ID") != null) {
                Integer teamId = Integer.parseInt(result.get("ID").toString());
                LANTeamEntity lanTeamEntity = lanTeamRepository.findById(teamId).orElse(null);
                if (lanTeamEntity != null) {
                    lanTeamEntity.setStatus(status);
                    lanTeamRepository.save(lanTeamEntity);
                }
            } else {
                throw new ResourceNotFoundException("Searched params doesn't exist");
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchPastEventsForParticipants(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = lanEventRepository.fetchPastEventsForParticipants(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchFutureEventsForParticipants(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = lanEventRepository.fetchFutureEventsForParticipants(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }
}
