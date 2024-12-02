/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.AudienceEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamMateEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.BadRequestException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.ResourceNotFoundException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.LANEventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Audience;
import com.thejackfolio.microservices.thejackfolio_db.models.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.models.LANTeam;
import com.thejackfolio.microservices.thejackfolio_db.models.Team;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.LANEventServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LANEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventService.class);

    @Autowired
    private LANEventMapper lanEventMapper;
    @Autowired
    private LANEventServiceHelper lanEventServiceHelper;

    public void saveOrUpdateEvent(LANEvent event, boolean isUpdate) {
        com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent lanEvent = lanEventServiceHelper.findLANEventByName(event.getName());
        if (isUpdate && lanEvent == null) {
            throw new ResourceNotFoundException("Event doesn't exist");
        }
        if (!isUpdate && lanEvent != null) {
            throw new BadRequestException("Event name already exist");
        }
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

    public List<LANTeam> fetchTeamWithTeamMate(String email) {
        List<TeamWithTeamMate> teamWithTeamMates = lanEventServiceHelper.fetchTeamWithTeamMate(email);
        return lanEventMapper.convertToTeamModels(teamWithTeamMates);
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        lanEventServiceHelper.updateTeamStatus(email, eventName, status);
    }

    public List<LANEvent> fetchPastEventsForParticipants(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchPastEventsForParticipants(email);
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public List<LANEvent> fetchFutureEventsForParticipants(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchFutureEventsForParticipants(email);
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public List<LANEvent> fetchPastEventsForAudience(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchPastEventsForAudience(email);
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public List<LANEvent> fetchFutureEventsForAudience(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchFutureEventsForAudience(email);
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public List<LANEvent> fetchLiveEventsForAudience(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchLiveEventsForAudience(email);
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public void saveOrUpdateAudience(Audience audience) {
        AudienceEntity audienceEntity = lanEventServiceHelper.fetchAudienceByEmailAndEventName(audience.getEmail(), audience.getEventName());
        AudienceEntity convertedEntity = lanEventMapper.convertToAudienceEntity(audience);
        if (audienceEntity == null) {
            LOGGER.info("Saving new audience details with payment status as {}, email as {} and eventName as {}", audience.getStatus(), audience.getEmail(), audience.getEventName());
        } else {
            LOGGER.info("Updating audience details with payment status as {}, email as {} and eventName as {}", audience.getStatus(), audience.getEmail(), audience.getEventName());
            convertedEntity.setId(audienceEntity.getId());
        }
        lanEventServiceHelper.saveAudience(convertedEntity);
    }

    public List<LANEvent> fetchInactiveEventForAdmin() {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.fetchInactiveEventForAdmin();
        return lanEventMapper.convertToLANEventModels(lanEvents);
    }

    public void updateEventStatus(String eventName, LANEventStatus status) {
        lanEventServiceHelper.updateEventStatus(eventName, status);
    }

    public List<LANTeam> fetchParticipatedTeamDetails(String eventName) {
        List<LANTeam> teams = new ArrayList<>();
        List<LANTeamEntity> teamEntities = lanEventServiceHelper.fetchParticipatedTeams(eventName);
        if (teamEntities != null && !teamEntities.isEmpty()) {
            for (LANTeamEntity lanTeamEntity : teamEntities) {
                List<LANTeamMateEntity> teamMateEntities = lanEventServiceHelper.fetchTeamMates(lanTeamEntity.getId());
                LANTeam lanTeam = lanEventMapper.convertToLANTeamModel(lanTeamEntity, teamMateEntities);
                teams.add(lanTeam);
            }
        }
        return teams;
    }
}
