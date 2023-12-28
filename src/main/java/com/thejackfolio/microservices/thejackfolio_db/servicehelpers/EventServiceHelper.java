/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.TeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.FileCreateException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.*;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
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

    public String findEventNameById(Integer eventId) throws DataBaseOperationException {
        String name = null;
        try {
            Events event = eventsRepository.findById(eventId).orElse(null);
            name = event.getName();
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return name;
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

    public List<Events> findAllEventsByEventIds(List<Integer> eventIds) throws DataBaseOperationException {
        List<Events> events = null;
        try {
            events = eventsRepository.findAllById(eventIds);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return events;
    }

    public List<TeamDetails> findTeamDetailsByEmail(String email) throws DataBaseOperationException {
        List<TeamDetails> details = null;
        try {
            details = teamDetailsRepository.findAllByEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return details;
    }

    public void updateEventStatus(String name, EventStatus status) throws DataBaseOperationException {
        try {
            eventsRepository.updateEventStatus(name, status);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<EventDetails> findEventDetailsForToday(Date today) throws DataBaseOperationException {
        List<EventDetails> details = null;
        try {
            details = detailsRepository.findAllByDate(today).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return details;
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

    public List<Teams> findAllTeamsByTeamIds(List<Integer> teamIds) throws DataBaseOperationException {
        List<Teams> teams = null;
        try {
            teams = teamsRepository.findAllById(teamIds);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teams;
    }

    public void updateTeamStatus(String teamName, TeamStatus status) throws DataBaseOperationException {
        try {
            teamsRepository.updateTeamStatus(teamName, status);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
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

    public List<Teams> findAllTeamsByEventId(Integer eventId) throws DataBaseOperationException {
        List<Teams> teams = null;
        try {
            teams = teamsRepository.findAllByEventId(eventId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return teams;
    }

    public byte[] generateExcel(List<Teams> teams) throws FileCreateException {
        byte[] outputStreamDoc = null;
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Teams");
            Row headingRow = sheet.createRow(0);
            Cell headingCellOne = headingRow.createCell(0);
            headingCellOne.setCellValue("TEAM NAMES");
            Cell headingCellTwo = headingRow.createCell(1);
            headingCellTwo.setCellValue("POINTS");
            int rowNum = 1;
            for (Teams team : teams) {
                Row row = sheet.createRow(rowNum++);
                Cell cellOne = row.createCell(0);
                if(team.getTeamStatus() == TeamStatus.FREE || team.getTeamStatus() == TeamStatus.PAID) {
                    cellOne.setCellValue(team.getName());
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
//            Uncomment below code to test in local whether sheet is created or not
//            try(OutputStream outputStream = new FileOutputStream("/Jack/ESportsManagementSystem/DOCUMENTS/LEADERBOARD/team_names.xlsx")) {
//                byteArrayOutputStream.writeTo(outputStream);
//            }
            outputStreamDoc = byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            LOGGER.info(StringConstants.FILE_ERROR, exception);
            throw new FileCreateException(StringConstants.DATABASE_ERROR, exception);
        }
        return outputStreamDoc;
    }
}
