/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Viewers;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.TeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.*;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.services.EventService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.Even;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Tag(name = "Event", description = "Event management APIs")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @Operation(
            summary = "Save OR Update events",
            description = "Save or Update events and gives the same event response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-event")
    public ResponseEntity<Event> saveOrUpdateEvent(@RequestBody Event event, @RequestParam boolean isCreate, @RequestParam boolean isUpdate) {
        try {
            if(event == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            event = service.saveOrUpdateEvent(event, isCreate, isUpdate);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException | EventException exception) {
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @Operation(
            summary = "Get event",
            description = "Get event with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-event/{name}")
    public ResponseEntity<Event> getEvent(@PathVariable String name) {
        Event event = null;
        try {
            if(name == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            event = service.getEvent(name);
            if(event.getMessage().equals(StringConstants.NAME_NOT_PRESENT)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(event);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            event = new Event();
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @Operation(
            summary = "Find upcoming events",
            description = "Find upcoming events with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-upcoming-events/{email}")
    public ResponseEntity<List<Event>> findUpcomingEvents(@PathVariable String email) {
        List<Event> eventResults = null;
        try {
            if(StringUtils.isEmpty(email) || StringUtils.isBlank(email)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            eventResults = service.findUpcomingEvents(email);
        } catch (DataBaseOperationException | MapperException exception) {
            eventResults = new ArrayList<>();
            Event event = new Event();
            event.setMessage(exception.getMessage());
            eventResults.add(event);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(eventResults);
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventResults);
    }

    @Operation(
            summary = "Update event status",
            description = "Update event status with a message which defines whether the request is successful or not."
    )
    @PostMapping("/update-event-status")
    public ResponseEntity<String> updateEventStatus(@RequestParam String status, @RequestParam String eventName) {
        try {
            if(StringUtils.isEmpty(eventName) || StringUtils.isBlank(eventName) || StringUtils.isEmpty(status) || StringUtils.isBlank(status)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.updateEventStatus(eventName, EventStatus.valueOf(status));
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(StringConstants.REQUEST_PROCESSED);
    }

    @Operation(
            summary = "Find today's events",
            description = "Find today's events with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-today-events")
    public ResponseEntity<List<Event>> findEventsScheduledForToday() {
        List<Event> events = null;
        try {
            events = service.findEventsScheduledForToday();
        } catch (DataBaseOperationException | MapperException exception) {
            events = new ArrayList<>();
            Event event = new Event();
            event.setMessage(exception.getMessage());
            events.add(event);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(events);
        }
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @Operation(
            summary = "Save OR Update teams",
            description = "Save or Update teams and gives the same team response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-team")
    public ResponseEntity<Team> saveOrUpdateTeam(@RequestBody Team team, @RequestParam boolean isCreate, @RequestParam boolean isUpdate) {
        try {
            if(team == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            team = service.saveOrUpdateTeam(team, isCreate, isUpdate);
            team.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException | TeamException exception) {
            team.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(team);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @Operation(
            summary = "Get team",
            description = "Get team with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-team/{name}")
    public ResponseEntity<Team> getTeam(@PathVariable String name) {
        Team team = null;
        try {
            if(name == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            team = service.getTeam(name);
            if(team.getMessage().equals(StringConstants.NAME_NOT_PRESENT)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(team);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            team = new Team();
            team.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(team);
        }
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @Operation(
            summary = "Update team status",
            description = "Update team stataus with a message which defines whether the request is successful or not."
    )
    @PostMapping("/update-team-status")
    public ResponseEntity<String> updateTeamStatus(@RequestParam String teamName, @RequestParam String teamStatus) {
        try {
            if(StringUtils.isBlank(teamName) || StringUtils.isEmpty(teamName) || StringUtils.isBlank(teamStatus) || StringUtils.isEmpty(teamStatus)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.updateTeamStatus(teamName, TeamStatus.valueOf(teamStatus));
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(StringConstants.REQUEST_PROCESSED);
    }

    @Operation(
            summary = "Save viewer",
            description = "Save viewer with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-viewer")
    public ResponseEntity<Viewer> saveViewer(@RequestBody Viewer viewer) {
        try {
            if(viewer == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveViewer(viewer);
            viewer.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            viewer.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(viewer);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(viewer);
    }

    @Operation(
            summary = "Is a viewer or not",
            description = "Is a viewer or not."
    )
    @GetMapping("/is-viewer")
    public ResponseEntity<Viewer> isViewer(@RequestParam String email, @RequestParam Integer eventId) {
        Viewer viewer = new Viewer();
        try {
            if(email == null || eventId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            boolean isViewer = service.isViewer(email, eventId);
            viewer.setEventId(eventId);
            viewer.setEmail(email);
            if(isViewer) {
                viewer.setMessage(StringConstants.IS_A_VIEWER);
            } else {
                viewer.setMessage(StringConstants.NOT_A_VIEWER);
            }
        } catch (DataBaseOperationException exception) {
            viewer.setEventId(eventId);
            viewer.setEmail(email);
            viewer.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(viewer);
        }
        return ResponseEntity.status(HttpStatus.OK).body(viewer);
    }

    @Operation(
            summary = "Save leaderboard",
            description = "Save leaderboard with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-leaderboard")
    public ResponseEntity<Leaderboard> saveLeaderboard(@RequestBody Leaderboard leaderboard) {
        try {
            if(leaderboard == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveLeaderboard(leaderboard);
            leaderboard.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            leaderboard.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(leaderboard);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboard);
    }

    @Operation(
            summary = "Save documents",
            description = "Save documents and gives the same documents response with a message which defines whether the request is successful or not."
    )
    @RequestMapping(path = "/save-documents/{eventId}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Leaderboard> saveLeaderboardDocument(@RequestPart MultipartFile doc, @PathVariable Integer eventId) {
        Leaderboard leaderboard = null;
        try {
            if(doc.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            leaderboard = service.saveLeaderboardDocument(doc, eventId);
            if(leaderboard == null) {
                leaderboard = new Leaderboard();
                leaderboard.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(leaderboard);
            }
            leaderboard.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (DataBaseOperationException | IOException | MapperException exception) {
            leaderboard = new Leaderboard();
            leaderboard.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(leaderboard);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboard);
    }

    @Operation(
            summary = "Get leaderboard",
            description = "Get leaderboard with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-leaderboard/{email}")
    public ResponseEntity<Leaderboard> findLeaderBoard(@RequestParam Integer eventId, @PathVariable String email) {
        Leaderboard leaderboard = null;
        try {
            if(eventId == null || email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            leaderboard = service.findLeaderboardByEventId(eventId, email);
            if(leaderboard == null) {
                leaderboard = new Leaderboard();
                leaderboard.setMessage(StringConstants.ID_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(leaderboard);
            }
        } catch (DataBaseOperationException | MapperException exception) {
            leaderboard = new Leaderboard();
            leaderboard.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(leaderboard);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboard);
    }

    @Operation(
            summary = "Get document",
            description = "Get document and gives the response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-document/{eventId}")
    public ResponseEntity<Document> findDoc(@PathVariable Integer eventId) {
        Document document = new Document();
        try {
            if(eventId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            byte[] doc = service.findLeaderBoardDoc(eventId);
            if(doc == null) {
                document.setMessage(StringConstants.ID_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(document);
            }
            document.setDocument(doc);
            document.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (DataBaseOperationException | IOException exception) {
            document.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(document);
        }
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    @Operation(
            summary = "Get team's document",
            description = "Get team's document and gives the response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/create-sheet/{eventId}")
    public ResponseEntity<byte[]> generateExcel(@PathVariable Integer eventId) {
        try {
            byte[] excelBytes = service.generateTeamNamesExcel(eventId);
            if(excelBytes == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String eventName = service.findEventNameById(eventId);
            headers.setContentDispositionFormData("attachment", eventName+".xlsx");
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (DataBaseOperationException | FileCreateException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
