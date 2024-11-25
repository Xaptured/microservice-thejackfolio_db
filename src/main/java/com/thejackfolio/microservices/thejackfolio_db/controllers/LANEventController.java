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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
