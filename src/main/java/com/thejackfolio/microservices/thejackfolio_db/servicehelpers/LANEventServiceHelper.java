/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.AudienceTicketEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.TeamWithTeamMate;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.LANDataBaseException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.ResourceNotFoundException;
import com.thejackfolio.microservices.thejackfolio_db.models.LANTeam;
import com.thejackfolio.microservices.thejackfolio_db.models.SubUser;
import com.thejackfolio.microservices.thejackfolio_db.repositories.*;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class LANEventServiceHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LANEventServiceHelper.class);

    @Autowired
    private LANEventRepository lanEventRepository;
    @Autowired
    private LANTeamRepository lanTeamRepository;
    @Autowired
    private LANTeamMateRepository lanTeamMateRepository;
    @Autowired
    private ClientCredentialsRepository clientCredentialsRepository;
    @Autowired
    private AudienceRepository audienceRepository;
    @Autowired
    private AudienceTicketRepository audienceTicketRepository;
    @Autowired
    private FailedPaymentRepository failedPaymentRepository;
    @Autowired
    private PendingPaymentRepository pendingPaymentRepository;
    @Autowired
    private InitiatePaymentRepository initiatePaymentRepository;
    @Autowired
    private SubUserRepository subUserRepository;

    public LANEvent saveLANEvent(LANEvent event) {
        try {
            return lanEventRepository.save(event);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public LANEvent findLANEventByName(String name) {
        try {
            return lanEventRepository.findByName(name).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEmailExist(String email) {
        try {
            return lanEventRepository.existsByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchFutureEventsWRTEmail(String email) {
        try {
            return lanEventRepository.fetchFutureEventsWRTEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchPastEventsWRTEmail(String email) {
        try {
            return lanEventRepository.fetchPastEventsWRTEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchLiveEventsWRTEmail(String email) {
        try {
            return lanEventRepository.fetchLiveEventsWRTEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public LANTeamEntity saveTeam(LANTeamEntity team) {
        try {
            return lanTeamRepository.save(team);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveTeamMates(List<LANTeamMateEntity> teamMates) {
        try {
            lanTeamMateRepository.saveAll(teamMates);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEmailExistInClientCredentials(String email) {
        try {
            return clientCredentialsRepository.existsByEmail(email);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANTeamMateEntity> checkEmailIsRegistered(List<LANTeamMateEntity> teamMateEntities) {
        for (LANTeamMateEntity teamMateEntity : teamMateEntities) {
            boolean isRegistered = isEmailExistInClientCredentials(teamMateEntity.getEmail());
            teamMateEntity.setEmailRegistered(isRegistered);
        }
        return teamMateEntities;
    }

    public List<TeamWithTeamMate> fetchTeamWithTeamMate(String email) {
        try {
            List<TeamWithTeamMate> teamWithTeamMates = new ArrayList<>();
            List<Map<String, Object>> results = lanTeamRepository.fetchTeamWithTeamMate(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    TeamWithTeamMate teamWithTeamMate = new TeamWithTeamMate();
                    teamWithTeamMate.setName(result.get("NAME").toString());
                    teamWithTeamMate.setEventName(result.get("EVENT_NAME").toString());
                    teamWithTeamMate.setEmail(result.get("EMAIL").toString());
                    teamWithTeamMate.setEmailRegistered((Boolean) result.get("EMAIL_REGISTERED"));
                    teamWithTeamMate.setStatus(LANTeamStatus.fromValue(Integer.parseInt(result.get("STATUS").toString())));
                    teamWithTeamMates.add(teamWithTeamMate);
                }
            }
            return teamWithTeamMates;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        try {
            Map<String, Object> result = lanTeamRepository.fetchTeamWithTeamMateAndEventName(email, eventName).orElse(null);
            if (result != null && result.get("ID") != null) {
                Integer teamId = Integer.parseInt(result.get("ID").toString());
                LANTeamEntity lanTeamEntity = lanTeamRepository.findById(teamId).orElse(null);
                if (lanTeamEntity != null) {
                    lanTeamEntity.setStatus(status);
                    lanTeamRepository.save(lanTeamEntity);
                }
            } else {
                throw new ResourceNotFoundException("Searched params doesn't exist");
            }
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Searched params doesn't exist");
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchPastEventsForParticipants(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = lanEventRepository.fetchPastEventsForParticipants(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchFutureEventsForParticipants(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = lanEventRepository.fetchFutureEventsForParticipants(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public AudienceEntity fetchAudienceByEmailAndEventName(String email, String eventName) {
        try {
            AudienceEntity audienceEntity = audienceRepository.findByEmailAndEventName(email, eventName).orElse(null);
            return audienceEntity;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveAudience(AudienceEntity entity) {
        try {
            audienceRepository.save(entity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchPastEventsForAudience(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = audienceRepository.fetchPastEventsForAudience(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchFutureEventsForAudience(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = audienceRepository.fetchFutureEventsForAudience(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchLiveEventsForAudience(String email) {
        try {
            List<LANEvent> lanEvents = new ArrayList<>();
            List<Map<String, Object>> results = audienceRepository.fetchLiveEventsForAudience(email).orElse(null);
            if (results!= null && !results.isEmpty()) {
                for(Map<String, Object> result : results) {
                    LANEvent lanEvent = new LANEvent();
                    lanEvent.setName(result.get("NAME").toString());
                    lanEvents.add(lanEvent);
                }
            }
            return lanEvents;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> findLANEventsNotRegisteredByAudience(String email) {
        try {
            return audienceRepository.findLANEventsNotRegisteredByAudience(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANEvent> fetchInactiveEventForAdmin() {
        try {
            return lanEventRepository.findByStatus(LANEventStatus.INACTIVE).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateEventStatus(String eventName, LANEventStatus status) {
        try {
            LANEvent lanEvent = lanEventRepository.findByName(eventName).orElse(null);
            if (lanEvent == null) {
                throw new ResourceNotFoundException("Event name doesn't exist");
            }
            lanEvent.setStatus(status);
            lanEventRepository.save(lanEvent);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Event name doesn't exist");
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANTeamEntity> fetchParticipatedTeams(String eventName) {
        try {
            return lanTeamRepository.findByEventName(eventName).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<LANTeamMateEntity> fetchTeamMates(int teamId) {
        try {
            return lanTeamMateRepository.findByTeamId(teamId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveAudienceTicket(AudienceTicketEntity audienceTicketEntity) {
        try {
            audienceTicketRepository.save(audienceTicketEntity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public AudienceTicketEntity fetchAudienceTicketByEmailAndEventName(String email, String eventName) {
        try {
            AudienceTicketEntity audienceTicketEntity = audienceTicketRepository.findByEmailAndEventName(email, eventName).orElse(null);
            return audienceTicketEntity;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public long fetchUnsentEmailForAudienceCount() {
        try {
            long count = audienceTicketRepository.countByEmailSent(false);
            return count;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<AudienceTicketEntity> fetchUnsentEmailForAudience() {
        try {
            List<AudienceTicketEntity> audienceTicketEntities = audienceTicketRepository.findByEmailSentFalse().orElse(null);
            return audienceTicketEntities;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateEmailSentStatus(Integer id) {
        try {
            audienceTicketRepository.updateEmailStatus(id);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateCheckedInStatus(String email, String eventName) {
        try {
            audienceTicketRepository.updateCheckedInStatus(email, eventName);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void savePendingPayment(PendingPaymentEntity entity) {
        try {
            pendingPaymentRepository.save(entity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveFailedPayment(FailedPaymentEntity entity) {
        try {
            failedPaymentRepository.save(entity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveInitiatePayment(InitiatePaymentEntity entity) {
        try {
            initiatePaymentRepository.save(entity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<PendingPaymentEntity> fetchAllPendingPayments() {
        try {
            List<PendingPaymentEntity> pendingPaymentEntities = pendingPaymentRepository.findAll();
            return pendingPaymentEntities;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<FailedPaymentEntity> fetchAllFailedPayments() {
        try {
            List<FailedPaymentEntity> failedPaymentEntities = failedPaymentRepository.findAll();
            return failedPaymentEntities;
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public InitiatePaymentEntity findByMerchantTransactionId(String merchantTransactionId) {
        try {
            return initiatePaymentRepository.findByMerchantTransactionId(merchantTransactionId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void deletePendingPaymentByEmailAndEventName(String email, String eventName) {
        try {
            PendingPaymentEntity pendingPaymentEntity = pendingPaymentRepository.findByEmailAndEventName(email, eventName).orElse(null);
            if (pendingPaymentEntity != null)
                pendingPaymentRepository.delete(pendingPaymentEntity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void deleteFailedPaymentByEmailAndEventName(String email, String eventName) {
        try {
            FailedPaymentEntity failedPaymentEntity = failedPaymentRepository.findByEmailAndEventName(email, eventName).orElse(null);
            if (failedPaymentEntity != null)
                failedPaymentRepository.delete(failedPaymentEntity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveSubUser(SubUserEntity subUserEntity) {
        try {
            subUserRepository.save(subUserEntity);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEmailAndEventNameExistForSubUser(String email, String eventName) {
        try {
            return subUserRepository.existsByEmailAndEventName(email, eventName);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public SubUserEntity findByEmailAndEventName(String email, String eventName) {
        try {
            return subUserRepository.findByEmailAndEventName(email, eventName).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isEventNameExistForSubUser(String eventName) {
        try {
            return subUserRepository.existsByEventName(eventName);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateActive(String eventName) {
        try {
            subUserRepository.updateActive(eventName);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void updateStartCheckInProcess(String eventName) {
        try {
            lanEventRepository.updateStartCheckInProcess(eventName);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<SubUserEntity> findByIsEmailSent(boolean isEmailSent) {
        try {
            return subUserRepository.findByIsEmailSent(isEmailSent).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public SubUserEntity findByUserName(String userName) {
        try {
            return subUserRepository.findByUserName(userName).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new LANDataBaseException(StringConstants.DATABASE_ERROR, exception);
        }
    }
}
