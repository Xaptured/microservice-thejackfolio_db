/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamMateEntity;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.ResourceNotFoundException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.LANEventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.models.LANTeam;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.LANEventServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LANEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventService.class);

    @Autowired
    private LANEventMapper lanEventMapper;
    @Autowired
    private LANEventServiceHelper lanEventServiceHelper;

    public void saveOrUpdateEvent(LANEvent event) {
        com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent lanEvent = lanEventServiceHelper.findLANEventByName(event.getName());

        com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent eventEntity = lanEventMapper.convertToLANEventEntity(event);
        if (lanEvent != null) {
            eventEntity.setId(lanEvent.getId());
        }
        lanEventServiceHelper.saveLANEvent(eventEntity);
    }

    public List<LANEvent> fetchFutureEventsWRTEmail(String email) {
        boolean emailExist = lanEventServiceHelper.isEmailExist(email);
        if (!emailExist) {
            throw new ResourceNotFoundException("Email ID doesn't exist");
        }
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> entities = lanEventServiceHelper.fetchFutureEventsWRTEmail(email);
        return lanEventMapper.convertToLANEventModels(entities);
    }

    public List<LANEvent> fetchPastEventsWRTEmail(String email) {
        boolean emailExist = lanEventServiceHelper.isEmailExist(email);
        if (!emailExist) {
            throw new ResourceNotFoundException("Email ID doesn't exist");
        }
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> entities = lanEventServiceHelper.fetchPastEventsWRTEmail(email);
        return lanEventMapper.convertToLANEventModels(entities);
    }

    public void saveTeams(List<LANTeam> teams) {
        for(LANTeam team: teams) {
            LANTeamEntity lanTeamEntity = lanEventMapper.convertToLANTeamEntity(team);
            lanTeamEntity = lanEventServiceHelper.saveTeam(lanTeamEntity);
            Integer teamId = lanTeamEntity.getId();
            List<LANTeamMateEntity> teamMateEntities = lanEventMapper.convertToLANTeamMateEntities(team.getTeamMates(), teamId);
            if (teamMateEntities != null) {
                teamMateEntities = lanEventServiceHelper.checkEmailIsRegistered(teamMateEntities);
                lanEventServiceHelper.saveTeamMates(teamMateEntities);
            }
        }
    }
}
