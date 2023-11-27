/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.TeamException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.EventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.EventServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventMapper mapper;
    @Autowired
    private EventServiceHelper helper;

    public Event saveOrUpdateEvent(Event event, boolean isCreate, boolean isUpdate) throws MapperException, DataBaseOperationException, EventException {
        Events nullOrEntity = helper.findEventByName(event.getName());
        if(isCreate) {
            if(nullOrEntity == null) {
                Events eventEntity = mapper.modelToEntityEvent(event);
                Events entity = helper.saveEvent(eventEntity);
                EventDetails detailEntity = mapper.modelToEntityDetails(event, entity.getId());
                helper.saveEventDetails(detailEntity);
                List<EventRules> ruleEntities = mapper.modelToEntityRules(event, entity.getId());
                helper.saveEventRules(ruleEntities);
                List<Rule> rules = mapper.entityToModelRules(ruleEntities);
                event.setRules(rules);
            } else {
                throw new EventException(StringConstants.DUPLICATE_ERROR);
            }
        }
        if(isUpdate) {
            if(nullOrEntity != null) {
                EventDetails detail = helper.findDetailsByEventId(nullOrEntity.getId());
                detail = mapper.modelToEntityDetails(event, detail);
                helper.saveEventDetails(detail);
                List<EventRules> eventRules = helper.findRulesByEventId(nullOrEntity.getId());
                List<Rule> rules = event.getRules();
                eventRules = mapper.modelToEntityRules(rules,eventRules, nullOrEntity.getId());
                helper.saveEventRules(eventRules);
                List<Rule> rulesModel = mapper.entityToModelRules(eventRules);
                event.setRules(rulesModel);
            } else {
                throw new EventException(StringConstants.UPDATE_ERROR);
            }
        }
        return event;
    }

    public Event getEvent(String name) throws DataBaseOperationException, MapperException {
        Event event = null;
        Events eventEntity = helper.findEventByName(name);
        if(eventEntity != null) {
            EventDetails detailEntity = helper.findDetailsByEventId(eventEntity.getId());
            List<EventRules> ruleEntities = helper.findRulesByEventId(eventEntity.getId());
            event = mapper.entityToModelEvent(eventEntity, detailEntity, ruleEntities);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } else {
            event = new Event();
            event.setMessage(StringConstants.NAME_NOT_PRESENT);
        }
        return event;
    }

    public Team saveOrUpdateTeam(Team team, boolean isCreate, boolean isUpdate) throws DataBaseOperationException, MapperException, TeamException {
        Teams teamEntity = helper.findTeamByName(team.getName());
        if(isCreate) {
            if(teamEntity == null) {
                teamEntity = mapper.modelToEntityTeam(team);
                teamEntity = helper.saveTeam(teamEntity);
                List<TeamDetails> details = mapper.modelToEntityDetails(team, teamEntity.getId());
                helper.saveTeamDetails(details);
                List<TeamDetail> teamDetails = mapper.entityToModelDetails(details);
                team.setDetail(teamDetails);
            } else {
                throw new TeamException(StringConstants.DUPLICATE_ERROR);
            }
        }
        if(isUpdate) {
            if(teamEntity != null) {
                List<TeamDetails> teamDetails = helper.findDetailsByTeamId(teamEntity.getId());
                List<TeamDetail> teamDetail = team.getDetail();
                teamDetails = mapper.modelToEntityDetails(teamDetail, teamDetails, teamEntity.getId());
                helper.saveTeamDetails(teamDetails);
                List<TeamDetail> detailModels = mapper.entityToModelDetails(teamDetails);
                team.setDetail(detailModels);
            } else {
                throw new TeamException(StringConstants.UPDATE_ERROR);
            }
        }
        return team;
    }

    public Team getTeam(String name) throws DataBaseOperationException, MapperException {
        Team team = null;
        Teams teamEntity = helper.findTeamByName(name);
        if(teamEntity != null) {
            List<TeamDetails> details = helper.findDetailsByTeamId(teamEntity.getId());
            team = mapper.entityToModelTeam(teamEntity, details);
            team.setMessage(StringConstants.REQUEST_PROCESSED);
        } else {
            team = new Team();
            team.setMessage(StringConstants.NAME_NOT_PRESENT);
        }
        return team;
    }

    public void saveViewer(Viewer viewer) throws MapperException, DataBaseOperationException {
        Viewers viewerEntity = mapper.modelToEntityViewer(viewer);
        helper.saveViewer(viewerEntity);
    }

    public boolean isViewer(String email, Integer eventId) throws DataBaseOperationException {
        return helper.isViewerForEvent(eventId, email);
    }
}
