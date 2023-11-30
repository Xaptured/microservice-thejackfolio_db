/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Viewers;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.TeamException;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.services.EventService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.View;
import java.io.IOException;

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
        return ResponseEntity.status(HttpStatus.OK).body(viewer);
    }

    @Operation(
            summary = "Is a viewer or not",
            description = "Is a viewer or not."
    )
    @GetMapping("/is-viewer")
    public ResponseEntity<Boolean> isViewer(@RequestParam String email, @RequestParam Integer eventId) {
        Boolean isViewer = false;
        try {
            if(email == null || eventId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            isViewer = service.isViewer(email, eventId);
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(isViewer);
        }
        return ResponseEntity.status(HttpStatus.OK).body(isViewer);
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
    @PostMapping("/save-documents/{eventId}")
    public ResponseEntity<Leaderboard> saveLeaderboardDocument(@RequestParam MultipartFile doc, @PathVariable Integer eventId) {
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
}
