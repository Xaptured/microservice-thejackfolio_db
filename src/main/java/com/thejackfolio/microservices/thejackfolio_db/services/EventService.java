/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.EventDetails;
import com.thejackfolio.microservices.thejackfolio_db.entities.EventRules;
import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.EventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Event;
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

    public void saveEvent(Event event, boolean isCreate, boolean isUpdate) throws MapperException, DataBaseOperationException, EventException {
        Events nullOrEntity = helper.findEventByName(event.getName());
        if(isCreate) {
            if(nullOrEntity == null) {
                Events eventEntity = mapper.modelToEntityEvent(event);
                Events entity = helper.saveEvent(eventEntity);
                EventDetails detailEntity = mapper.modelToEntityDetails(event, entity.getId());
                helper.saveEventDetails(detailEntity);
                List<EventRules> ruleEntities = mapper.modelToEntityRules(event, entity.getId());
                helper.saveEventRules(ruleEntities);
            } else {
                throw new EventException(StringConstants.EVENT_ERROR);
            }
        }
        if(isUpdate) {
            if(nullOrEntity != null) {
                EventDetails detail = helper.findDetailsByEventId(nullOrEntity.getId());
                detail = mapper.modelToEntityDetails(event, detail);
                helper.saveEventDetails(detail);
                List<EventRules> rules = helper.findRulesByEventId(nullOrEntity.getId());
                rules = mapper.modelToEntityRules(event, rules);
                helper.saveEventRules(rules);
            } else {
                throw new EventException(StringConstants.EVENT_UPDATE_ERROR);
            }
        }
    }

    public Event getEvent(String name) throws DataBaseOperationException, MapperException {
        Event event = null;
        Events eventEntity = helper.findEventByName(name);
        if(eventEntity != null) {
            EventDetails detailEntity = helper.findDetailsByEventId(eventEntity.getId());
            List<EventRules> ruleEntities = helper.findRulesByEventId(eventEntity.getId());
            event = mapper.entityToModelEvent(eventEntity, detailEntity, ruleEntities);
        }
        return event;
    }
}
