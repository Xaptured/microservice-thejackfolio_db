/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.models.LANEvent;
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
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody LANEvent event) {
        lanEventService.saveOrUpdateEvent(event);
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
}
