/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.TeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.*;
import com.thejackfolio.microservices.thejackfolio_db.mappers.EventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.EventServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.GameServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventMapper mapper;
    @Autowired
    private EventServiceHelper helper;
    @Autowired
    private GameServiceHelper gameServiceHelper;

    public Event saveOrUpdateEvent(Event event, boolean isCreate, boolean isUpdate) throws MapperException, DataBaseOperationException, EventException {
        Events nullOrEntity = helper.findEventByName(event.getName());
        if(isCreate) {
            if(nullOrEntity == null) {
                Events eventEntity = mapper.modelToEntityEvent(event);
                Events entity = helper.saveEvent(eventEntity);
                EventDetails detailEntity = mapper.modelToEntityDetails(event, entity.getId());
                helper.saveEventDetails(detailEntity);
                List<EventRules> ruleEntities = mapper.modelToEntityRules(event, entity.getId());
                helper.saveEventRules(ruleEntities);
                List<Rule> rules = mapper.entityToModelRules(ruleEntities);
                event.setRules(rules);
            } else {
                throw new EventException(StringConstants.DUPLICATE_ERROR);
            }
        }
        if(isUpdate) {
            if(nullOrEntity != null) {
                EventDetails detail = helper.findDetailsByEventId(nullOrEntity.getId());
                detail = mapper.modelToEntityDetails(event, detail);
                helper.saveEventDetails(detail);
                List<EventRules> eventRules = helper.findRulesByEventId(nullOrEntity.getId());
                List<Rule> rules = event.getRules();
                eventRules = mapper.modelToEntityRules(rules,eventRules, nullOrEntity.getId());
                helper.saveEventRules(eventRules);
                List<Rule> rulesModel = mapper.entityToModelRules(eventRules);
                event.setRules(rulesModel);
            } else {
                throw new EventException(StringConstants.UPDATE_ERROR);
            }
        }
        return event;
    }

    public Event getEvent(String name) throws DataBaseOperationException, MapperException {
        Event event = null;
        Events eventEntity = helper.findEventByName(name);
        if(eventEntity != null) {
            EventDetails detailEntity = helper.findDetailsByEventId(eventEntity.getId());
            List<EventRules> ruleEntities = helper.findRulesByEventId(eventEntity.getId());
            event = mapper.entityToModelEvent(eventEntity, detailEntity, ruleEntities);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } else {
            event = new Event();
            event.setMessage(StringConstants.NAME_NOT_PRESENT);
        }
        return event;
    }

    public Integer getEventId(String name) throws DataBaseOperationException {
        Integer eventId = null;
        Events eventEntity = helper.findEventByName(name);
        if(eventEntity != null) {
            eventId = eventEntity.getId();
        }
        return eventId;
    }

    public Boolean isRegisteredInEvent(Integer eventId, String eventName, String email) throws DataBaseOperationException {
        Boolean isRegistered = false;
        if(eventId == null)
            eventId = helper.findEventByName(eventName).getId();
        List<Teams> teamEntities = helper.findAllTeamsByEventId(eventId);
        for(Teams team : teamEntities) {
            List<TeamDetails> detailEntities = helper.findDetailsByTeamId(team.getId());
            isRegistered = detailEntities.stream()
                    .anyMatch(detail -> detail.getEmail().equals(email));
            if(isRegistered)
                return isRegistered;
        }
        return isRegistered;
    }

    public List<TeamDetail> getTeamDetailsForEvent(Integer eventId, String eventName, String email) throws DataBaseOperationException, MapperException {
        Boolean isRegistered = false;
        List<TeamDetail> teamDetails = null;
        if(eventId == null)
            eventId = helper.findEventByName(eventName).getId();
        List<Teams> teamEntities = helper.findAllTeamsByEventId(eventId);
        for(Teams team : teamEntities) {
            List<TeamDetails> detailEntities = helper.findDetailsByTeamId(team.getId());
            isRegistered = detailEntities.stream()
                    .anyMatch(detail -> detail.getEmail().equals(email));
            if(isRegistered) {
                teamDetails = mapper.entityToModelDetails(detailEntities);
                return teamDetails;
            }
        }
        return null;
    }

    public Integer remainingPlayersPerSlotCount(Integer eventId, String eventName, String email) throws DataBaseOperationException, MapperException {
        if(eventId == null)
            eventId = helper.findEventByName(eventName).getId();
        EventDetails eventDetailsEntity = helper.findDetailsByEventId(eventId);
        List<TeamDetail> teamDetails = getTeamDetailsForEvent(eventId, eventName, email);
        Integer remainingCount = eventDetailsEntity.getPlayersPerSlot() - teamDetails.size();
        return remainingCount;
    }

    public List<TeamWithCount> getTeamsWithCount(Integer eventId, String eventName) throws DataBaseOperationException {
        List<TeamWithCount> teamWithCounts = null;
        if(eventId == null)
            eventId = helper.findEventByName(eventName).getId();
        List<Teams> teamEntities = helper.findAllTeamsByEventId(eventId);
        EventDetails eventDetailsEntity = helper.findDetailsByEventId(eventId);
        if(teamEntities != null && !teamEntities.isEmpty()) {
            teamWithCounts = new ArrayList<>();
            for(Teams teamEntity : teamEntities) {
                TeamWithCount teamWithCount = new TeamWithCount();
                teamWithCount.setTeamName(teamEntity.getName());
                List<TeamDetails> detailEntities = helper.findDetailsByTeamId(teamEntity.getId());
                Integer remainingCount = eventDetailsEntity.getPlayersPerSlot() - detailEntities.size();
                teamWithCount.setRemainingPlayers(remainingCount);
                teamWithCounts.add(teamWithCount);
            }
        }
        return teamWithCounts;
    }

    public String findEventNameById(Integer eventId) throws DataBaseOperationException {
        return helper.findEventNameById(eventId);
    }

    public List<Event> findUpcomingEvents(String email) throws DataBaseOperationException, MapperException {
        List<Event> activeEvents = null;
        List<TeamDetails> details = helper.findTeamDetailsByEmail(email);
        if(!details.isEmpty()) {
            List<Integer> teamIds = new ArrayList<>();
            for(TeamDetails detail : details) {
                teamIds.add(detail.getTeamId());
            }
            List<Teams> teams = helper.findAllTeamsByTeamIds(teamIds);
            List<Integer> eventIds = new ArrayList<>();
            for(Teams team : teams) {
                eventIds.add(team.getEventId());
            }
            List<Events> events = helper.findAllEventsByEventIds(eventIds);
            activeEvents = new ArrayList<>();
            for(Events event : events) {
                if(event.getStatus() == EventStatus.ACTIVE) {
                    activeEvents.add(mapper.entityToModelEvent(event));
                }
            }
        }
        return activeEvents;
    }

    public List<Event> findActiveUpcomingEventsWrtInterestedGames(String email) throws DataBaseOperationException, MapperException {
        List<Event> activeEvents = null;
        List<InterestedGames> interestedGameEntities = gameServiceHelper.findInterestedGamesByEmail(email);
        List<Integer> gameIds = null;
        List<String> gameNames = null;
        if(!interestedGameEntities.isEmpty()) {
            gameIds = new ArrayList<>();
            gameNames = new ArrayList<>();
            for(InterestedGames gameEntity : interestedGameEntities) {
                gameNames.add(gameEntity.getGameName());
            }
            List<Games> gameEntities = gameServiceHelper.findAllByGameNames(gameNames);
            if(!gameEntities.isEmpty()) {
                for(Games gameEntity: gameEntities) {
                    gameIds.add(gameEntity.getId());
                }
                List<Events> eventEntities = helper.findActiveEventsWrtInterestedGames(gameIds);
                activeEvents = mapper.entityToModelEvent(eventEntities);
            }
        }
        return activeEvents;
    }

    public void updateEventStatus(String name, EventStatus status) throws DataBaseOperationException {
        helper.updateEventStatus(name, status);
    }

    public List<Event> findEventsScheduledForToday() throws DataBaseOperationException, MapperException {
        LocalDate todayLocalDate = LocalDate.now();
        Date todaySQLDate = Date.valueOf(todayLocalDate);
        List<EventDetails> eventDetails = helper.findEventDetailsForToday(todaySQLDate);
        Map<Integer, EventDetails> eventDetailsMap = new HashMap<>();
        List<Integer> eventIds = new ArrayList<>();
        for(EventDetails eventDetail : eventDetails) {
            eventIds.add(eventDetail.getEventId());
            eventDetailsMap.put(eventDetail.getEventId(), eventDetail);
        }
        List<Events> events = helper.findAllEventsByEventIds(eventIds);
        List<Event> eventModels = new ArrayList<>();
        for(Events eventEntity : events) {
            eventModels.add(mapper.entityToModelEvent(eventEntity, eventDetailsMap.get(eventEntity.getId()), null));
        }
        return eventModels;
    }

    public Team saveOrUpdateTeam(Team team, boolean isCreate, boolean isUpdate) throws DataBaseOperationException, MapperException, TeamException {
        Teams teamEntity = helper.findTeamByName(team.getName());
        if(isCreate) {
            if(teamEntity == null) {
                teamEntity = mapper.modelToEntityTeam(team);
                teamEntity = helper.saveTeam(teamEntity);
                List<TeamDetails> details = mapper.modelToEntityDetails(team, teamEntity.getId());
                helper.saveTeamDetails(details);
                List<TeamDetail> teamDetails = mapper.entityToModelDetails(details);
                team.setDetail(teamDetails);
                EventDetails detail = helper.findDetailsByEventId(team.getEventId());
                if(detail != null) {
                    Integer remainingSlotsUpdated = detail.getRemainingSlots()-1;
                    detail.setRemainingSlots(remainingSlotsUpdated);
                }
            } else {
                throw new TeamException(StringConstants.DUPLICATE_ERROR);
            }
        }
        if(isUpdate) {
            if(teamEntity != null) {
                List<TeamDetails> teamDetails = helper.findDetailsByTeamId(teamEntity.getId());
                List<TeamDetail> teamDetail = team.getDetail();
                teamDetails = mapper.modelToEntityDetails(teamDetail, teamDetails, teamEntity.getId());
                helper.saveTeamDetails(teamDetails);
                List<TeamDetail> detailModels = mapper.entityToModelDetails(teamDetails);
                team.setDetail(detailModels);
            } else {
                throw new TeamException(StringConstants.UPDATE_ERROR);
            }
        }
        return team;
    }

    public Team getTeam(String name) throws DataBaseOperationException, MapperException {
        Team team = null;
        Teams teamEntity = helper.findTeamByName(name);
        if(teamEntity != null) {
            List<TeamDetails> details = helper.findDetailsByTeamId(teamEntity.getId());
            team = mapper.entityToModelTeam(teamEntity, details);
            team.setMessage(StringConstants.REQUEST_PROCESSED);
        } else {
            team = new Team();
            team.setMessage(StringConstants.NAME_NOT_PRESENT);
        }
        return team;
    }

    public void updateTeamStatus(String teamName, TeamStatus status) throws DataBaseOperationException {
        helper.updateTeamStatus(teamName, status);
    }

    public void saveViewer(Viewer viewer) throws MapperException, DataBaseOperationException {
        Viewers viewerEntity = mapper.modelToEntityViewer(viewer);
        helper.saveViewer(viewerEntity);
    }

    public boolean isViewer(String email, Integer eventId) throws DataBaseOperationException {
        return helper.isViewerForEvent(eventId, email);
    }

    public void saveLeaderboard(Leaderboard leaderboard) throws DataBaseOperationException, MapperException {
        Leaderboards leaderboardEntity = mapper.modelToEntityLeaderBoard(leaderboard);
        helper.saveLeaderboard(leaderboardEntity);
    }
    public Leaderboard saveLeaderboardDocument(MultipartFile doc, Integer eventId) throws DataBaseOperationException, MapperException, IOException {
        Leaderboard leaderboard = null;
        Leaderboards leaderboardEntity = helper.findLeaderboardByEventId(eventId);
        if(leaderboardEntity != null) {
            leaderboardEntity = mapper.modelToEntityLeaderBoard(leaderboardEntity, doc);
            helper.saveLeaderboard(leaderboardEntity);
            saveDocumentsToPath(leaderboardEntity.getDocumentPath(), doc);
            leaderboard = mapper.entityToModelLeaderboard(leaderboardEntity);
        }
        return leaderboard;
    }

    private void saveDocumentsToPath(String docPath, MultipartFile document) throws IOException {
        document.transferTo(new File(docPath).toPath());
    }

    public Leaderboard findLeaderboardByEventId(Integer eventId, String email) throws DataBaseOperationException, MapperException {
        Leaderboard leaderboard = null;
        Leaderboards leaderboardEntity = helper.findLeaderboardByEventId(eventId);
        if(leaderboardEntity != null) {
            List<TeamDetails> details = helper.findDetailsByTeamId(leaderboardEntity.getTeamId());
            boolean isWon = false;
            for(TeamDetails detail : details) {
                if(detail.getEmail().equals(email)) {
                    isWon = true;
                    break;
                }
            }
            leaderboard = mapper.entityToModelLeaderboard(leaderboardEntity);
            if(isWon) {
                leaderboard.setMessage(StringConstants.CONGRATULATIONS);
            } else {
                leaderboard.setMessage(StringConstants.BETTER_LUCK);
            }
        }
        return leaderboard;
    }

    public byte[] findLeaderBoardDoc(Integer eventId) throws IOException, DataBaseOperationException {
        Leaderboards leaderboardEntity = helper.findLeaderboardByEventId(eventId);
        if(leaderboardEntity != null) {
            String docPath = leaderboardEntity.getDocumentPath();
            byte[] document = Files.readAllBytes(new File(docPath).toPath());
            return document;
        } else {
            return null;
        }
    }

    public byte[] generateTeamNamesExcel(Integer eventId) throws DataBaseOperationException, FileCreateException {
        List<Teams> teams = helper.findAllTeamsByEventId(eventId);
        return helper.generateExcel(teams);
    }
}
