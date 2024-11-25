/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANMapperException;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LANEventMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventMapper.class);

    public LANEvent convertToLANEventEntity(com.thejackfolio.microservices.thejackfolio_db.models.LANEvent lanEventModel) {
        try {
            if (lanEventModel != null) {
                LANEvent lanEvent = new LANEvent();
                lanEvent.setGameName(lanEventModel.getGameName());
                lanEvent.setName(lanEventModel.getName());
                lanEvent.setAddressLineOne(lanEventModel.getAddress().getAddressLineOne());
                lanEvent.setAddressLineTwo(lanEventModel.getAddress().getAddressLineTwo());
                lanEvent.setCity(lanEventModel.getAddress().getCity());
                lanEvent.setState(lanEventModel.getAddress().getState());
                lanEvent.setZipCode(lanEventModel.getAddress().getZipCode());
                lanEvent.setEventType(lanEventModel.getEventDetails().getEventType());
                lanEvent.setTotalSlots(lanEventModel.getEventDetails().getTotalSlots());
                lanEvent.setPrizePool(lanEventModel.getEventDetails().getPrizePool());
                lanEvent.setDate(lanEventModel.getEventDetails().getDate());
                lanEvent.setStatus(lanEventModel.getEventStatus());
                return lanEvent;
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return null;
    }
}
