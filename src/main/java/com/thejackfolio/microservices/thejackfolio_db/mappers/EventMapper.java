/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.services.GameService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class EventMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMapper.class);

    @Autowired
    private GameService gameService;


    public Events modelToEntityEvent(Event event) throws MapperException, DataBaseOperationException {
        Events eventEntity = null;
        try {
            if(event != null) {
                eventEntity = new Events();
                eventEntity.setEmail(event.getEmail());
                eventEntity.setName(event.getName());
                eventEntity.setStatus(event.getStatus());
                Integer gameId = gameService.getGameId(event.getGameName());
                eventEntity.setGameId(gameId);
            }
        } catch (DataBaseOperationException exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return eventEntity;
    }

    public EventDetails modelToEntityDetails(Event event, Integer eventId) throws MapperException {
        EventDetails detailEntity = null;
        try {
            if(event != null) {
                detailEntity = new EventDetails();
                detailEntity.setEventId(eventId);
                detailEntity.setDate(Date.valueOf(event.getDate()));
                detailEntity.setTime(Time.valueOf(event.getTime()));
                detailEntity.setDuration(Time.valueOf(event.getDuration()));
                detailEntity.setPlayersPerSlot(event.getPlayersPerSlot());
                detailEntity.setSlotCount(event.getSlotCount());
                detailEntity.setRemainingSlots(event.getRemainingSlots());
                detailEntity.setType(event.getType());
                detailEntity.setPrizePool(event.getPrizePool());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return detailEntity;
    }

    public EventDetails modelToEntityDetails(Event event, EventDetails entityDetails) throws MapperException {
        try {
            if(event != null) {
                entityDetails.setDate(Date.valueOf(event.getDate()));
                entityDetails.setTime(Time.valueOf(event.getTime()));
                entityDetails.setDuration(Time.valueOf(event.getDuration()));
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return entityDetails;
    }

    public List<EventRules> modelToEntityRules(Event event, Integer eventId) throws MapperException {
        List<EventRules> ruleEntities = new ArrayList<>();
        try {
            if(event != null && event.getRules() != null && !event.getRules().isEmpty()) {
                int counter = 1;
                for(Rule rule : event.getRules()) {
                    EventRules ruleEntity = new EventRules();
                    ruleEntity.setEventId(eventId);
                    ruleEntity.setDescription(rule.getDescription());
                    ruleEntity.setRuleNumber(counter++);
                    ruleEntities.add(ruleEntity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return ruleEntities;
    }

    public List<EventRules> modelToEntityRules(List<Rule> rules, List<EventRules> eventRules, Integer eventId) throws MapperException {
        List<EventRules> ruleEntities = null;
        try {
            if(rules != null && !rules.isEmpty()) {
                ruleEntities = new ArrayList<>();
                int counter = findMaxRuleNumber(eventRules) + 1;
                for(Rule rule : rules) {
                    EventRules ruleEntity;
                    if(rule.getRuleNumber() == null) {
                        ruleEntity = new EventRules();
                        ruleEntity.setEventId(eventId);
                        ruleEntity.setDescription(rule.getDescription());
                        ruleEntity.setRuleNumber(counter++);
                    } else {
                        ruleEntity = searchEventRuleByRuleNumber(eventRules, rule.getRuleNumber());
                        if(ruleEntity != null) {
                            ruleEntity.setDescription(rule.getDescription());
                        }
                    }
                    ruleEntities.add(ruleEntity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return ruleEntities;
    }

    private Integer findMaxRuleNumber(List<EventRules> rules) {
        int maxRuleNumber = 0;
        Optional<EventRules> maxEventRule = rules.stream().max(Comparator.comparingInt(EventRules::getRuleNumber));
        if(maxEventRule.isPresent()) {
            maxRuleNumber = maxEventRule.get().getRuleNumber();
        }
        return maxRuleNumber;
    }

    private EventRules searchEventRuleByRuleNumber(List<EventRules> rules, Integer ruleNumber) {
        for(EventRules rule: rules) {
            if(Objects.equals(rule.getRuleNumber(), ruleNumber)) {
                return rule;
            }
        }
        return null;
    }

    public List<Rule> entityToModelRules(List<EventRules> ruleEntities) throws MapperException {
        List<Rule> rules = null;
        try {
            if(ruleEntities != null) {
                rules = new ArrayList<>();
                for(EventRules entity : ruleEntities) {
                    Rule rule = new Rule();
                    rule.setRuleNumber(entity.getRuleNumber());
                    rule.setDescription(entity.getDescription());
                    rules.add(rule);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return rules;
    }

    public Event entityToModelEvent(Events eventEntity) throws MapperException {
        Event event = null;
        try {
            if(eventEntity != null) {
                event = new Event();
                event.setName(eventEntity.getName());
                event.setEmail(eventEntity.getEmail());
                String gameName = gameService.getGameName(eventEntity.getGameId());
                event.setGameName(gameName);
                event.setStatus(eventEntity.getStatus());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return event;
    }

    public List<Event> entityToModelEvent(List<Events> eventEntities) throws MapperException {
        List<Event> eventModel = null;
        try {
            if(eventEntities != null && !eventEntities.isEmpty()) {
                eventModel = new ArrayList<>();
                for(Events entity : eventEntities) {
                    Event event = new Event();
                    event.setName(entity.getName());
                    eventModel.add(event);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return eventModel;
    }

    public Event entityToModelEvent(Events eventEntity, EventDetails detailEntity, List<EventRules> ruleEntities) throws MapperException {
        Event event = null;
        try {
            if(eventEntity != null) {
                event = new Event();
                event.setName(eventEntity.getName());
                event.setEmail(eventEntity.getEmail());
                String gameName = gameService.getGameName(eventEntity.getGameId());
                event.setGameName(gameName);
                event.setStatus(eventEntity.getStatus());
            }
            if(detailEntity != null) {
                event.setDate(detailEntity.getDate().toString());
                event.setTime(detailEntity.getTime().toString());
                event.setDuration(detailEntity.getDuration().toString());
                event.setPlayersPerSlot(detailEntity.getPlayersPerSlot());
                event.setSlotCount(detailEntity.getSlotCount());
                event.setRemainingSlots(detailEntity.getRemainingSlots());
                event.setType(detailEntity.getType());
                event.setPrizePool(detailEntity.getPrizePool());
            }
            if(ruleEntities != null) {
                List<Rule> rules = entityToModelRules(ruleEntities);
                event.setRules(rules);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return event;
    }

    public Teams modelToEntityTeam(Team team) throws MapperException {
        Teams teamEntity = null;
        try {
            if(team != null) {
                teamEntity = new Teams();
                teamEntity.setName(team.getName());
                teamEntity.setEventId(team.getEventId());
                teamEntity.setTeamStatus(team.getTeamStatus());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return teamEntity;
    }

    public List<TeamDetails> modelToEntityDetails(Team team, Integer teamId) throws MapperException {
        List<TeamDetails> teamDetails = null;
        try {
            if(team != null) {
                teamDetails = new ArrayList<>();
                int counter = 1;
                for(TeamDetail detail : team.getDetail()) {
                    TeamDetails details = new TeamDetails();
                    details.setEmail(detail.getEmail());
                    details.setTeamId(teamId);
                    details.setPlayerNumber(counter++);
                    teamDetails.add(details);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return teamDetails;
    }

    public List<TeamDetail> entityToModelDetails(List<TeamDetails> detailEntities) throws MapperException {
        List<TeamDetail> details = null;
        try {
            if(detailEntities != null) {
                details = new ArrayList<>();
                for (TeamDetails teamDetails : detailEntities) {
                    TeamDetail teamDetail = new TeamDetail();
                    teamDetail.setEmail(teamDetails.getEmail());
                    teamDetail.setPlayerNumber(teamDetails.getPlayerNumber());
                    details.add(teamDetail);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return details;
    }

    public Team entityToModelTeam(Teams teamEntity) throws MapperException {
        Team team = null;
        try {
            if(teamEntity != null) {
                team = new Team();
                team.setName(teamEntity.getName());
                team.setEventId(teamEntity.getEventId());
                team.setTeamStatus(teamEntity.getTeamStatus());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return team;
    }

    public Team entityToModelTeam(Teams teamEntity, List<TeamDetails> detailEntities) throws MapperException {
        Team team = null;
        try {
            if(teamEntity != null && detailEntities != null) {
                team = new Team();
                team.setName(teamEntity.getName());
                team.setEventId(teamEntity.getEventId());
                team.setTeamStatus(teamEntity.getTeamStatus());
                List<TeamDetail> details = entityToModelDetails(detailEntities);
                team.setDetail(details);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return team;
    }

    public List<TeamDetails> modelToEntityDetails(List<TeamDetail> details, List<TeamDetails> detailEntities, Integer teamId) throws MapperException {
        List<TeamDetails> teamDetails = null;
        try {
            if(details != null) {
                teamDetails = new ArrayList<>();
                int counter = findMaxPlayerNumber(detailEntities) + 1;
                for(TeamDetail detail : details) {
                    TeamDetails teamDetail;
                    if(detail.getPlayerNumber() == null) {
                        teamDetail = new TeamDetails();
                        teamDetail.setTeamId(teamId);
                        teamDetail.setPlayerNumber(counter++);
                        teamDetail.setEmail(detail.getEmail());
                    } else {
                        teamDetail = searchTeamDetailsByPlayerNumber(detailEntities, detail.getPlayerNumber());
                        if(teamDetail != null) {
                            teamDetail.setEmail(detail.getEmail());
                        }
                    }
                    teamDetails.add(teamDetail);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return teamDetails;
    }

    private Integer findMaxPlayerNumber(List<TeamDetails> details) {
        int maxPlayerNumber = 0;
        Optional<TeamDetails> maxNumber = details.stream().max(Comparator.comparingInt(TeamDetails::getPlayerNumber));
        if(maxNumber.isPresent()) {
            maxPlayerNumber = maxNumber.get().getPlayerNumber();
        }
        return maxPlayerNumber;
    }

    private TeamDetails searchTeamDetailsByPlayerNumber(List<TeamDetails> details, Integer playerNumber) {
        for(TeamDetails teamDetail: details) {
            if(Objects.equals(teamDetail.getPlayerNumber(), playerNumber)) {
                return teamDetail;
            }
        }
        return null;
    }

    public Viewers modelToEntityViewer(Viewer viewer) throws MapperException {
        Viewers viewerEntity = null;
        try {
            if(viewer != null) {
                viewerEntity = new Viewers();
                viewerEntity.setEmail(viewer.getEmail());
                viewerEntity.setEventId(viewer.getEventId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return viewerEntity;
    }

    public Viewer entityToModelViewer(Viewers viewerEntity) throws MapperException {
        Viewer viewer = null;
        try {
            if(viewerEntity != null) {
                viewer = new Viewer();
                viewer.setEmail(viewerEntity.getEmail());
                viewer.setEventId(viewerEntity.getEventId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return viewer;
    }

    public Leaderboards modelToEntityLeaderBoard(Leaderboard leaderboard) throws MapperException {
        Leaderboards leaderboardEntity = null;
        try {
            if(leaderboard != null) {
                leaderboardEntity = new Leaderboards();
                leaderboardEntity.setEventId(leaderboard.getEventId());
                leaderboardEntity.setTeamId(leaderboard.getTeamId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return leaderboardEntity;
    }

    public Leaderboards modelToEntityLeaderBoard(Leaderboard leaderboard, Leaderboards leaderboardEntity) throws MapperException {
        try {
            if(leaderboard != null && leaderboardEntity != null) {
                leaderboardEntity.setTeamId(leaderboard.getTeamId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return leaderboardEntity;
    }

    public Leaderboards modelToEntityLeaderBoard(Leaderboards entity, MultipartFile doc) throws MapperException {
        try {
            if(entity != null) {
                entity.setDocumentName(doc.getOriginalFilename());
                entity.setDocumentType(doc.getContentType());
                entity.setDocumentPath(StringConstants.LEADERBOARD_FOLDER_PATH + doc.getOriginalFilename());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return entity;
    }

    public Leaderboard entityToModelLeaderboard(Leaderboards leaderboardEntity) throws MapperException {
        Leaderboard leaderboard = null;
        try {
            if(leaderboardEntity != null) {
                leaderboard = new Leaderboard();
                leaderboard.setEventId(leaderboardEntity.getEventId());
                leaderboard.setTeamId(leaderboardEntity.getTeamId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return leaderboard;
    }
}
