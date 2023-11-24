/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.EventDetails;
import com.thejackfolio.microservices.thejackfolio_db.entities.EventRules;
import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Event;
import com.thejackfolio.microservices.thejackfolio_db.models.Rule;
import com.thejackfolio.microservices.thejackfolio_db.services.GameService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Event entityToModelEvent(Events eventEntity, EventDetails detailEntity, List<EventRules> ruleEntities) throws MapperException {
        Event event = null;
        try {
            if(eventEntity != null && detailEntity != null && ruleEntities != null) {
                event = new Event();
                event.setName(eventEntity.getName());
                event.setEmail(eventEntity.getEmail());
                String gameName = gameService.getGameName(eventEntity.getGameId());
                event.setGameName(gameName);
                event.setStatus(eventEntity.getStatus());
                event.setDate(detailEntity.getDate().toString());
                event.setTime(detailEntity.getTime().toString());
                event.setDuration(detailEntity.getDuration().toString());
                event.setPlayersPerSlot(detailEntity.getPlayersPerSlot());
                event.setSlotCount(detailEntity.getSlotCount());
                event.setRemainingSlots(detailEntity.getRemainingSlots());
                event.setType(detailEntity.getType());
                event.setPrizePool(detailEntity.getPrizePool());
                List<Rule> rules = new ArrayList<>();
                for(EventRules ruleEntity: ruleEntities) {
                    Rule rule = new Rule();
                    rule.setRuleNumber(ruleEntity.getRuleNumber());
                    rule.setDescription(ruleEntity.getDescription());
                    rules.add(rule);
                }
                event.setRules(rules);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return event;
    }
}
