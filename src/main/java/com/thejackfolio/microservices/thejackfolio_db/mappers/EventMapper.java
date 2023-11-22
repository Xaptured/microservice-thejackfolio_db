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
import com.thejackfolio.microservices.thejackfolio_db.services.GameService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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

    public List<EventRules> modelToEntityRules(Event event, Integer eventId) throws MapperException {
        List<EventRules> ruleEntities = new ArrayList<>();
        try {
            if(event != null && event.getRules() != null && !event.getRules().isEmpty()) {
                for(String rule : event.getRules()) {
                    EventRules ruleEntity = new EventRules();
                    ruleEntity.setEventId(eventId);
                    ruleEntity.setDescription(rule);
                    ruleEntities.add(ruleEntity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return ruleEntities;
    }
}
