/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.AudienceEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamMateEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANMapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LANEventMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventMapper.class);

    public LANEvent convertToLANEventEntity(com.thejackfolio.microservices.thejackfolio_db.models.LANEvent lanEventModel) {
        try {
            if (lanEventModel != null) {
                LANEvent lanEvent = new LANEvent();
                lanEvent.setEmail(lanEventModel.getEmail());
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

    public List<com.thejackfolio.microservices.thejackfolio_db.models.LANEvent> convertToLANEventModels(List<LANEvent> entities) {
        List<com.thejackfolio.microservices.thejackfolio_db.models.LANEvent> lanEventModels = new ArrayList<>();
        try {
            if (!entities.isEmpty()) {
                for (LANEvent lanEventEntity: entities) {
                    com.thejackfolio.microservices.thejackfolio_db.models.LANEvent model = convertToLANEventModel(lanEventEntity);
                    if (model != null) {
                        lanEventModels.add(model);
                    }
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return lanEventModels;
    }

    public com.thejackfolio.microservices.thejackfolio_db.models.LANEvent convertToLANEventModel(LANEvent entity) {
        try {
            if (entity != null) {
                com.thejackfolio.microservices.thejackfolio_db.models.LANEvent model = new com.thejackfolio.microservices.thejackfolio_db.models.LANEvent();
                model.setName(entity.getName());
                model.setEmail(entity.getEmail());
                model.setGameName(entity.getGameName());
                model.setAddress(new LANAddress(entity.getAddressLineOne(), entity.getAddressLineTwo(), entity.getCity(), entity.getState(), entity.getZipCode()));
                model.setEventStatus(entity.getStatus());
                model.setEventDetails(new LANEventDetails(entity.getEventType(), entity.getPrizePool(), entity.getTotalSlots(), entity.getDate()));
                return model;
            } return null;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
    }

    public LANTeamEntity convertToLANTeamEntity(LANTeam teamModel) {
        try {
            if (teamModel != null) {
                LANTeamEntity entity = new LANTeamEntity();
                entity.setTeamName(teamModel.getTeamName());
                entity.setEventName(teamModel.getEventName());
                entity.setStatus(teamModel.getStatus());
                return entity;
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return null;
    }

    public List<LANTeamMateEntity> convertToLANTeamMateEntities(List<LANTeamMate> lanTeamMates, Integer teamId) {
        List<LANTeamMateEntity> teamMateEntities = null;
        try {
            if (!lanTeamMates.isEmpty()) {
                teamMateEntities = new ArrayList<>();
                for (LANTeamMate lanTeamMate: lanTeamMates) {
                    LANTeamMateEntity lanTeamMateEntity = new LANTeamMateEntity();
                    lanTeamMateEntity.setEmail(lanTeamMate.getEmail());
                    lanTeamMateEntity.setEmailRegistered(lanTeamMateEntity.isEmailRegistered());
                    lanTeamMateEntity.setTeamId(teamId);
                    teamMateEntities.add(lanTeamMateEntity);
                }
                return teamMateEntities;
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return null;
    }

    public List<LANTeam> convertToTeamModels(List<TeamWithTeamMate> teamWithTeamMates) {
        List<LANTeam> lanTeams = new ArrayList<>();
        try {
            if (teamWithTeamMates != null && !teamWithTeamMates.isEmpty()) {
                for (TeamWithTeamMate teamWithTeamMate : teamWithTeamMates) {
                    LANTeamMate lanTeamMate = new LANTeamMate();
                    List<LANTeamMate> lanTeamMates = new ArrayList<>();
                    lanTeamMate.setEmail(teamWithTeamMate.getEmail());
                    lanTeamMate.setIsEmailRegistered(teamWithTeamMate.isEmailRegistered());
                    lanTeamMates.add(lanTeamMate);

                    LANTeam lanTeam = new LANTeam();
                    lanTeam.setEventName(teamWithTeamMate.getEventName());
                    lanTeam.setTeamName(teamWithTeamMate.getName());
                    lanTeam.setStatus(teamWithTeamMate.getStatus());
                    lanTeam.setTeamMates(lanTeamMates);
                    lanTeams.add(lanTeam);
                }
            }
            return lanTeams;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
    }

    public AudienceEntity convertToAudienceEntity(Audience audience) {
        AudienceEntity audienceEntity = null;
        try {
            if (audience != null) {
                audienceEntity = new AudienceEntity();
                audienceEntity.setAmount(audience.getAmount());
                audienceEntity.setTransactionId(audience.getTransactionId());
                audienceEntity.setName(audience.getName());
                audienceEntity.setStatus(audience.getStatus());
                audienceEntity.setEmail(audience.getEmail());
                audienceEntity.setEventName(audience.getEventName());
            }
            return audienceEntity;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
    }
}
