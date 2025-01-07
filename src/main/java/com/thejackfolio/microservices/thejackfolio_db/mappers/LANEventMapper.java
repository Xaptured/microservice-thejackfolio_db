/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.AudienceTicketEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANMapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.repositories.FailedPaymentRepository;
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
                lanEvent.setStartCheckInProcess(lanEvent.isStartCheckInProcess());
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
                    LANTeam lanTeam = convertToLANTeamModel(teamWithTeamMate);
                    lanTeams.add(lanTeam);
                }
            }
            return lanTeams;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
    }

    public LANTeam convertToLANTeamModel(TeamWithTeamMate teamWithTeamMate) {
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
        return lanTeam;
    }

    public AudienceEntity convertToAudienceEntity(Audience audience) {
        AudienceEntity audienceEntity = null;
        try {
            if (audience != null) {
                audienceEntity = new AudienceEntity();
                audienceEntity.setAmount(audience.getAmount());
                audienceEntity.setTransactionId(audience.getTransactionId());
                audienceEntity.setMerchantTransactionId(audience.getMerchantTransactionId());
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

    public LANTeam convertToLANTeamModel(LANTeamEntity lanTeamEntity, List<LANTeamMateEntity> lanTeamMateEntities) {
        LANTeam lanTeam = null;
        if (lanTeamEntity != null) {
            lanTeam = new LANTeam();
            lanTeam.setTeamName(lanTeamEntity.getTeamName());
            lanTeam.setEventName(lanTeamEntity.getEventName());
            lanTeam.setStatus(lanTeamEntity.getStatus());
            lanTeam.setTeamMates(convertToLANTeamMateModels(lanTeamMateEntities));
        }
        return lanTeam;
    }

    public List<LANTeamMate> convertToLANTeamMateModels(List<LANTeamMateEntity> lanTeamMateEntities) {
        List<LANTeamMate> lanTeamMates = new ArrayList<>();
        if (lanTeamMateEntities != null && !lanTeamMateEntities.isEmpty()) {
            for (LANTeamMateEntity lanTeamMateEntity : lanTeamMateEntities) {
                LANTeamMate lanTeamMate = new LANTeamMate();
                lanTeamMate.setEmail(lanTeamMateEntity.getEmail());
                lanTeamMate.setIsEmailRegistered(lanTeamMateEntity.isEmailRegistered());
                lanTeamMates.add(lanTeamMate);
            }
        }
        return lanTeamMates;
    }

    public AudienceTicketEntity convertToAudienceTicketEntity(AudienceTicket audienceTicket) {
        AudienceTicketEntity audienceTicketEntity = null;
        try {
            if (audienceTicket != null) {
                audienceTicketEntity = new AudienceTicketEntity();
                audienceTicketEntity.setEmail(audienceTicket.getEmail());
                audienceTicketEntity.setEventName(audienceTicket.getEventName());
                audienceTicketEntity.setTicketNumber(audienceTicket.getTicketNumber());
                audienceTicketEntity.setEmailSent(audienceTicket.isEmailSent());
                audienceTicketEntity.setCheckedIn(audienceTicket.isCheckedIn());
            }
            return audienceTicketEntity;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
    }

    public List<AudienceTicket> convertToAudienceTicketModels(List<AudienceTicketEntity> entities) {
        List<AudienceTicket> audienceTickets = null;
        if (entities != null && !entities.isEmpty()) {
            audienceTickets = new ArrayList<>();
            for (AudienceTicketEntity audienceTicketEntity : entities) {
                AudienceTicket audienceTicket = convertToAudienceTicketModel(audienceTicketEntity);
                audienceTickets.add(audienceTicket);
            }
        }
        return audienceTickets;
    }

    public AudienceTicket convertToAudienceTicketModel(AudienceTicketEntity entity) {
        AudienceTicket audienceTicket = null;
        try {
            if (entity != null) {
                audienceTicket = new AudienceTicket();
                audienceTicket.setEmail(entity.getEmail());
                audienceTicket.setTicketNumber(entity.getTicketNumber());
                audienceTicket.setEmailSent(entity.isEmailSent());
                audienceTicket.setEventName(entity.getEventName());
                audienceTicket.setCheckedIn(entity.isCheckedIn());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return audienceTicket;
    }

    public PendingPaymentEntity convertAudienceToPendingPaymentEntity(Audience audience) {
        PendingPaymentEntity pendingPaymentEntity = null;
        try {
            if (audience != null) {
                pendingPaymentEntity = new PendingPaymentEntity();
                pendingPaymentEntity.setAmount(audience.getAmount());
                pendingPaymentEntity.setEventName(audience.getEventName());
                pendingPaymentEntity.setEmail(audience.getEmail());
                pendingPaymentEntity.setName(audience.getName());
                pendingPaymentEntity.setStatus(audience.getStatus());
                pendingPaymentEntity.setMerchantTransactionId(audience.getMerchantTransactionId());
                pendingPaymentEntity.setRefund(audience.isRefund());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return pendingPaymentEntity;
    }

    public FailedPaymentEntity convertAudienceToFailedPaymentEntity(Audience audience) {
        FailedPaymentEntity failedPaymentEntity = null;
        try {
            if (audience != null) {
                failedPaymentEntity = new FailedPaymentEntity();
                failedPaymentEntity.setAmount(audience.getAmount());
                failedPaymentEntity.setEventName(audience.getEventName());
                failedPaymentEntity.setEmail(audience.getEmail());
                failedPaymentEntity.setName(audience.getName());
                failedPaymentEntity.setStatus(audience.getStatus());
                failedPaymentEntity.setMerchantTransactionId(audience.getMerchantTransactionId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return failedPaymentEntity;
    }

    public InitiatePaymentEntity convertAudienceToInitiatePaymentEntity(Audience audience) {
        InitiatePaymentEntity initiatePaymentEntity = null;
        try {
            if (audience != null) {
                initiatePaymentEntity = new InitiatePaymentEntity();
                initiatePaymentEntity.setAmount(audience.getAmount());
                initiatePaymentEntity.setEventName(audience.getEventName());
                initiatePaymentEntity.setEmail(audience.getEmail());
                initiatePaymentEntity.setName(audience.getName());
                initiatePaymentEntity.setMerchantTransactionId(audience.getMerchantTransactionId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return initiatePaymentEntity;
    }

    public List<Audience> convertPendingPaymentEntitiesToAudiences(List<PendingPaymentEntity> pendingPaymentEntities) {
        List<Audience> audiences = new ArrayList<>();
        if (!pendingPaymentEntities.isEmpty()) {
            for (PendingPaymentEntity pendingPaymentEntity : pendingPaymentEntities) {
                Audience audience = convertPendingPaymentEntityToAudience(pendingPaymentEntity);
                audiences.add(audience);
            }
        }
        return audiences;
    }

    public Audience convertPendingPaymentEntityToAudience(PendingPaymentEntity pendingPaymentEntity) {
        Audience audience = null;
        try {
            if (pendingPaymentEntity != null) {
                audience = new Audience();
                audience.setAmount(pendingPaymentEntity.getAmount());
                audience.setEmail(pendingPaymentEntity.getEmail());
                audience.setEventName(pendingPaymentEntity.getEventName());
                audience.setName(pendingPaymentEntity.getName());
                audience.setMerchantTransactionId(pendingPaymentEntity.getMerchantTransactionId());
                audience.setStatus(pendingPaymentEntity.getStatus());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return audience;
    }

    public List<Audience> convertFailedPaymentEntitiesToAudiences(List<FailedPaymentEntity> failedPaymentEntities) {
        List<Audience> audiences = new ArrayList<>();
        if (!failedPaymentEntities.isEmpty()) {
            for (FailedPaymentEntity failedPaymentEntity : failedPaymentEntities) {
                Audience audience = convertFailedPaymentEntityToAudience(failedPaymentEntity);
                audiences.add(audience);
            }
        }
        return audiences;
    }

    public Audience convertFailedPaymentEntityToAudience(FailedPaymentEntity failedPaymentEntity) {
        Audience audience = null;
        try {
            if (failedPaymentEntity != null) {
                audience = new Audience();
                audience.setAmount(failedPaymentEntity.getAmount());
                audience.setEmail(failedPaymentEntity.getEmail());
                audience.setEventName(failedPaymentEntity.getEventName());
                audience.setName(failedPaymentEntity.getName());
                audience.setMerchantTransactionId(failedPaymentEntity.getMerchantTransactionId());
                audience.setStatus(failedPaymentEntity.getStatus());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return audience;
    }

    public Audience convertInitiatePaymentEntityToAudience(InitiatePaymentEntity initiatePaymentEntity) {
        Audience audience = null;
        try {
            if (initiatePaymentEntity != null) {
                audience = new Audience();
                audience.setAmount(initiatePaymentEntity.getAmount());
                audience.setEmail(initiatePaymentEntity.getEmail());
                audience.setEventName(initiatePaymentEntity.getEventName());
                audience.setName(initiatePaymentEntity.getName());
                audience.setMerchantTransactionId(initiatePaymentEntity.getMerchantTransactionId());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return audience;
    }

    public SubUserEntity convertSubUserModelToEntity(SubUser subUser) {
        SubUserEntity entity = null;
        try {
            if (subUser != null) {
                entity = new SubUserEntity();
                entity.setEmail(subUser.getEmail());
                entity.setUserPassword(subUser.getUserPassword());
                entity.setName(subUser.getName());
                entity.setUserName(subUser.getUserName());
                entity.setEventName(subUser.getEventName());
                entity.setEmailSent(subUser.isEmailSent());
                entity.setActive(subUser.isActive());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return entity;
    }

    public SubUserEntity convertSubUserModelToEntity(SubUser subUser, SubUserEntity subUserEntity) {
        try {
            if (subUser != null) {
                subUserEntity.setUserPassword(subUser.getUserPassword());
                subUserEntity.setUserName(subUser.getUserName());
                subUserEntity.setEmailSent(subUser.isEmailSent());
                subUserEntity.setActive(subUser.isActive());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return subUserEntity;
    }

    public SubUser convertSubUserEntityToModel(SubUserEntity subUserEntity) {
        SubUser subUser = null;
        try {
            if (subUserEntity != null) {
                subUser = new SubUser();
                subUser.setEmail(subUserEntity.getEmail());
                subUser.setUserPassword(subUserEntity.getUserPassword());
                subUser.setName(subUserEntity.getName());
                subUser.setUserName(subUserEntity.getUserName());
                subUser.setEventName(subUserEntity.getEventName());
                subUser.setEmailSent(subUserEntity.isEmailSent());
                subUser.setActive(subUserEntity.isActive());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return subUser;
    }

    public List<SubUser> convertSubUserEntitiesToModels(List<SubUserEntity> subUserEntities) {
        List<SubUser> subUsers = new ArrayList<>();;
        try {
            if (!subUserEntities.isEmpty()) {
                for (SubUserEntity entity : subUserEntities) {
                    SubUser subUser = convertSubUserEntityToModel(entity);
                    subUsers.add(subUser);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new LANMapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return subUsers;
    }
}
