/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Event;
import com.thejackfolio.microservices.thejackfolio_db.services.EventService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Event", description = "Event management APIs")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @Operation(
            summary = "Save events",
            description = "Save events and gives the same event response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-event")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        try {
            if(event == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveEvent(event);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}
