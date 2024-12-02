/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.models.Audience;
import com.thejackfolio.microservices.thejackfolio_db.models.LANEvent;
import com.thejackfolio.microservices.thejackfolio_db.models.LANTeam;
import com.thejackfolio.microservices.thejackfolio_db.services.LANEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event-LAN", description = "Event LAN management APIs")
@RestController
@RequestMapping("/events-lan")
public class LANEventController {

    @Autowired
    private LANEventService lanEventService;

    @Operation(
            summary = "Save OR Update lan events",
            description = "Save OR Update lan events."
    )
    @PostMapping("/save-or-update-event")
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody LANEvent event, @RequestParam boolean isUpdate) {
        lanEventService.saveOrUpdateEvent(event, isUpdate);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch future events with respect to email",
            description = "Fetch future events with respect to email."
    )
    @GetMapping("/future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsWRTEmail(@PathVariable String email) {
        List<LANEvent> lanEvents = lanEventService.fetchFutureEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvents);
    }

    @Operation(
            summary = "Fetch past events with respect to email",
            description = "Fetch past events with respect to email."
    )
    @GetMapping("/past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsWRTEmail(@PathVariable String email) {
        List<LANEvent> lanEvents = lanEventService.fetchPastEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvents);
    }

    @Operation(
            summary = "Save team and team mate details",
            description = "Save team and team mate details."
    )
    @PostMapping("/save-teams")
    public ResponseEntity<Void> saveTeams(@RequestBody List<LANTeam> team) {
        lanEventService.saveTeams(team);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch pending teams with respect to email",
            description = "Fetch pending teams with respect to email."
    )
    @GetMapping("/pending-teams/{email}")
    public ResponseEntity<List<LANTeam>> fetchTeamWithTeamMate(@PathVariable String email) {
        List<LANTeam> lanTeams = lanEventService.fetchTeamWithTeamMate(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Update team status",
            description = "Update team status."
    )
    @PostMapping("/update-team-status")
    public ResponseEntity<Void> updateTeamStatus(@RequestParam String email, @RequestParam String eventName, @RequestParam LANTeamStatus status) {
        lanEventService.updateTeamStatus(email, eventName, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch past events for participant with respect to email",
            description = "Fetch past events for participant with respect to email."
    )
    @GetMapping("/participant-past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsForParticipants(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchPastEventsForParticipants(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch future events for participant with respect to email",
            description = "Fetch future events for participant with respect to email."
    )
    @GetMapping("/participant-future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsForParticipants(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchFutureEventsForParticipants(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Save audience with payment status",
            description = "Save audience with payment status."
    )
    @PostMapping("/save-or-update-audience")
    public ResponseEntity<Void> saveOrUpdateAudience(@RequestBody Audience audience) {
        lanEventService.saveOrUpdateAudience(audience);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch past events for audience with respect to email",
            description = "Fetch past events for audience with respect to email."
    )
    @GetMapping("/audience-past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchPastEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch future events for audience with respect to email",
            description = "Fetch future events for audience with respect to email."
    )
    @GetMapping("/audience-future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchFutureEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch live events for audience with respect to email",
            description = "Fetch live events for audience with respect to email."
    )
    @GetMapping("/audience-live-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchLiveEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchLiveEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch inactive events for admin",
            description = "Fetch inactive events for admin."
    )
    @GetMapping("/admin-inactive-events")
    public ResponseEntity<List<LANEvent>> fetchInactiveEventForAdmin() {
        List<LANEvent> lanTeams = lanEventService.fetchInactiveEventForAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Update event status",
            description = "Update event status."
    )
    @PostMapping("/update-event-status")
    public ResponseEntity<Void> updateEventStatus(@RequestParam String eventName, @RequestParam LANEventStatus status) {
        lanEventService.updateEventStatus(eventName, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
