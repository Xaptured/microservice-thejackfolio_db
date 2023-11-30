/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.*;
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
    @Autowired
    private TeamsRepository teamsRepository;
    @Autowired
    private TeamDetailsRepository teamDetailsRepository;
    @Autowired
    private ViewerRepository viewerRepository;
    @Autowired
    private LeaderboardsRepository leaderboardsRepository;

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

    public Events findEventByName(String name) throws DataBaseOperationException {
        Events eventEntity = null;
        try {
            eventEntity = eventsRepository.findByName(name).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return eventEntity;
    }

    public EventDetails findDetailsByEventId(Integer eventId) throws DataBaseOperationException {
         EventDetails detailEntity = null;
         try {
             detailEntity = detailsRepository.findByEventId(eventId).orElse(null);
         } catch (Exception exception) {
             LOGGER.info(StringConstants.DATABASE_ERROR, exception);
             throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
         }
         return detailEntity;
    }

    public List<EventRules> findRulesByEventId(Integer eventId) throws DataBaseOperationException {
        List<EventRules> ruleEntities = null;
        try {
            ruleEntities = eventRulesRepository.findAllByEventId(eventId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return ruleEntities;
    }

    public Teams saveTeam(Teams team) throws DataBaseOperationException {
        Teams teamEntity = null;
        try {
            teamEntity = teamsRepository.save(team);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teamEntity;
    }

    public void saveTeamDetails(List<TeamDetails> details) throws DataBaseOperationException {
        try {
            teamDetailsRepository.saveAll(details);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public Teams findTeamByName(String name) throws DataBaseOperationException {
        Teams teamEntity = null;
        try {
            teamEntity = teamsRepository.findByName(name).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teamEntity;
    }

    public Teams findTeamById(Integer id) throws DataBaseOperationException {
        Teams teamEntity = null;
        try {
            teamEntity = teamsRepository.findById(id).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teamEntity;
    }

    public List<TeamDetails> findDetailsByTeamId(Integer teamId) throws DataBaseOperationException {
        List<TeamDetails> teamDetails = null;
        try {
            teamDetails = teamDetailsRepository.findAllByTeamId(teamId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teamDetails;
    }

    public void saveViewer(Viewers viewer) throws DataBaseOperationException {
        try {
            viewerRepository.save(viewer);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public boolean isViewerForEvent(Integer eventId, String email) throws DataBaseOperationException {
        Viewers viewer = null;
        try {
            viewer = viewerRepository.findViewer(eventId, email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return viewer != null;
    }

    public void saveLeaderboard(Leaderboards leaderboard) throws DataBaseOperationException {
        try {
            leaderboardsRepository.save(leaderboard);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public Leaderboards findLeaderboardByEventId(Integer eventId) throws DataBaseOperationException {
        Leaderboards leaderboard = null;
        try {
            leaderboard = leaderboardsRepository.findByEventId(eventId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return leaderboard;
    }
}
