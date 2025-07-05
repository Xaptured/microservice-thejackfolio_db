/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.AudienceTicketEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.BadRequestException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANDataBaseException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.ResourceNotFoundException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.LANEventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.models.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.LANEventServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
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

    public List<LANEvent> fetchLiveEventsWRTEmail(String email) {
        boolean emailExist = lanEventServiceHelper.isEmailExist(email);
        if (!emailExist) {
            throw new ResourceNotFoundException("Email ID doesn't exist");
        }
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> entities = lanEventServiceHelper.fetchLiveEventsWRTEmail(email);
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

    public List<LANEvent> findLANEventsNotRegisteredByAudience(String email) {
        List<com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent> lanEvents = lanEventServiceHelper.findLANEventsNotRegisteredByAudience(email);
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

    public LANEvent fetchLANEventDetails(String eventName) {
        com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent lanEvent = lanEventServiceHelper.findLANEventByName(eventName);
        return lanEventMapper.convertToLANEventModel(lanEvent);
    }

    public void saveAudienceTicket(AudienceTicket audienceTicket) {
        AudienceTicketEntity audienceTicketEntity = lanEventServiceHelper.fetchAudienceTicketByEmailAndEventName(audienceTicket.getEmail(), audienceTicket.getEventName());
        AudienceTicketEntity convertedEntity = lanEventMapper.convertToAudienceTicketEntity(audienceTicket);
        if (audienceTicketEntity == null) {
            LOGGER.info("Saving new audience ticket details with email as {} and eventName as {}", audienceTicket.getEmail(), audienceTicket.getEventName());
        } else {
            LOGGER.info("Can not process saving new audience ticket as details already exist for  email as {} and eventName as {}", audienceTicket.getEmail(), audienceTicket.getEventName());
            throw new BadRequestException("Details already exist");
        }
        lanEventServiceHelper.saveAudienceTicket(convertedEntity);
    }

    public long fetchUnsentEmailForAudienceCount() {
        return lanEventServiceHelper.fetchUnsentEmailForAudienceCount();
    }

    public List<AudienceTicket> fetchUnsentEmailForAudience() {
        List<AudienceTicketEntity> entities = lanEventServiceHelper.fetchUnsentEmailForAudience();
        return lanEventMapper.convertToAudienceTicketModels(entities);
    }

    public void updateEmailStatus(AudienceTicket audienceTicket) {
        AudienceTicketEntity audienceTicketEntity = lanEventServiceHelper.fetchAudienceTicketByEmailAndEventName(audienceTicket.getEmail(), audienceTicket.getEventName());
        if (audienceTicketEntity != null) {
            lanEventServiceHelper.updateEmailSentStatus(audienceTicketEntity.getId());
        } else {
            LOGGER.info("Can not process update status as details not exist for email as {} and eventName as {}", audienceTicket.getEmail(), audienceTicket.getEventName());
            throw new BadRequestException("Details doesn't exist");
        }
    }

    public void updateCheckedInStatus(String email, String eventName) {
        lanEventServiceHelper.updateCheckedInStatus(email, eventName);
    }

    public AudienceTicket fetchAudienceTicketByEventNameAndEmail(String email, String eventName) {
        AudienceTicketEntity audienceTicketEntity = lanEventServiceHelper.fetchAudienceTicketByEmailAndEventName(email, eventName);
        if (audienceTicketEntity != null) {
            return lanEventMapper.convertToAudienceTicketModel(audienceTicketEntity);
        } else {
            throw new BadRequestException("Details doesn't exist");
        }
    }

    public void savePendingPayment(Audience audience) {
        PendingPaymentEntity pendingPaymentEntity = lanEventMapper.convertAudienceToPendingPaymentEntity(audience);
        lanEventServiceHelper.savePendingPayment(pendingPaymentEntity);
    }

    public void saveFailedPayment(Audience audience) {
        FailedPaymentEntity failedPaymentEntity = lanEventMapper.convertAudienceToFailedPaymentEntity(audience);
        lanEventServiceHelper.saveFailedPayment(failedPaymentEntity);
    }

    public void saveInitiatePayment(Audience audience) {
        InitiatePaymentEntity initiatePaymentEntity = lanEventMapper.convertAudienceToInitiatePaymentEntity(audience);
        lanEventServiceHelper.saveInitiatePayment(initiatePaymentEntity);
    }

    public List<Audience> fetchAllPendingPayments() {
        List<PendingPaymentEntity> pendingPaymentEntities = lanEventServiceHelper.fetchAllPendingPayments();
        return lanEventMapper.convertPendingPaymentEntitiesToAudiences(pendingPaymentEntities);
    }

    public List<Audience> fetchAllFailedPayments() {
        List<FailedPaymentEntity> failedPaymentEntities = lanEventServiceHelper.fetchAllFailedPayments();
        return lanEventMapper.convertFailedPaymentEntitiesToAudiences(failedPaymentEntities);
    }

    public Audience fetchInitiatePayment(String merchantTransactionId) {
        InitiatePaymentEntity initiatePaymentEntity = lanEventServiceHelper.findByMerchantTransactionId(merchantTransactionId);
        return lanEventMapper.convertInitiatePaymentEntityToAudience(initiatePaymentEntity);
    }

    public void deletePendingPaymentByEmailAndEventName(String email, String eventName) {
        lanEventServiceHelper.deletePendingPaymentByEmailAndEventName(email, eventName);
    }

    public void deleteFailedPaymentByEmailAndEventName(String email, String eventName) {
        lanEventServiceHelper.deleteFailedPaymentByEmailAndEventName(email, eventName);
    }

    public void saveSubUser(SubUser subUser) {
        boolean isExist = lanEventServiceHelper.isEmailAndEventNameExistForSubUser(subUser.getEmail(), subUser.getEventName());
        if (isExist) {
            throw new BadRequestException("Event name and email id already exist");
        } else {
            SubUserEntity subUserEntity = lanEventMapper.convertSubUserModelToEntity(subUser);
            lanEventServiceHelper.saveSubUser(subUserEntity);
        }
    }

    public void updateSubUser(SubUser subUser) {
        boolean isExist = lanEventServiceHelper.isEmailAndEventNameExistForSubUser(subUser.getEmail(), subUser.getEventName());
        if (isExist) {
            SubUserEntity subUserEntity = lanEventServiceHelper.findByEmailAndEventName(subUser.getEmail(), subUser.getEventName());
            subUserEntity = lanEventMapper.convertSubUserModelToEntity(subUser, subUserEntity);
            lanEventServiceHelper.saveSubUser(subUserEntity);
        } else {
            throw new ResourceNotFoundException("Email ID and event name doesn't exist");
        }
    }

    public void updateActive(String eventName) {
        boolean isExist = lanEventServiceHelper.isEventNameExistForSubUser(eventName);
        if (isExist) {
            lanEventServiceHelper.updateActive(eventName);
            lanEventServiceHelper.updateStartCheckInProcess(eventName);
        } else {
            throw new ResourceNotFoundException("Event name doesn't exist");
        }
    }

    public List<SubUser> findByIsEmailSent() {
        List<SubUserEntity> subUserEntities = lanEventServiceHelper.findByIsEmailSent(false);
        return lanEventMapper.convertSubUserEntitiesToModels(subUserEntities);
    }

    public SubUser findByUserName(String userName) {
        SubUserEntity subUserEntity = lanEventServiceHelper.findByUserName(userName);
        if (subUserEntity == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        return lanEventMapper.convertSubUserEntityToModel(subUserEntity);
    }

    public void updateFeedback(List<Feedback> feedbacks) {
        for (Feedback feedback : feedbacks) {
            FeedbackEntity feedbackEntity = lanEventServiceHelper.findFeedbackByEmail(feedback.getEmail());
            if (feedbackEntity != null) {
                feedbackEntity = lanEventMapper.convertFeedbackModelToEntity(feedback, feedbackEntity);
                lanEventServiceHelper.saveFeedback(feedbackEntity);
            } else {
                throw new ResourceNotFoundException("Email doesn't exist");
            }
        }
    }

    public Feedback findFeedback(String email) {
        FeedbackEntity feedbackEntity = lanEventServiceHelper.findFeedbackByEmail(email);
        if (feedbackEntity != null) {
            Feedback feedback = lanEventMapper.convertFeedbackEntityToModel(feedbackEntity);
            return feedback;
        } else {
            throw new ResourceNotFoundException("Email doesn't exist");
        }
    }

    public List<Feedback> getFeedbackExactlyOneMonthOld() {
        List<FeedbackEntity> entities = lanEventServiceHelper.getFeedbackExactlyOneMonthOld();
        return lanEventMapper.convertFeedbackEntitiesToModels(entities);
    }
}
