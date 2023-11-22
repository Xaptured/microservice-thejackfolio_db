/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.EventDetails;
import com.thejackfolio.microservices.thejackfolio_db.entities.EventRules;
import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.EventDetailsRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.EventRulesRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.EventsRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceHelper.class);

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private EventDetailsRepository detailsRepository;
    @Autowired
    private EventRulesRepository eventRulesRepository;

    public Events saveEvent(Events event) throws DataBaseOperationException {
        Events eventEntity = null;
        try {
            eventEntity = eventsRepository.save(event);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return eventEntity;
    }

    public void saveEventDetails(EventDetails details) throws DataBaseOperationException {
        try {
            detailsRepository.save(details);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveEventRules(List<EventRules> rules) throws DataBaseOperationException {
        try {
            eventRulesRepository.saveAll(rules);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }
}
