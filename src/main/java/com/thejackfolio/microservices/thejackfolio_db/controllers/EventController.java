/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
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
            summary = "Save OR Update events",
            description = "Save or Update events and gives the same event response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-event")
    public ResponseEntity<Event> saveOrUpdateEvent(@RequestBody Event event, @RequestParam boolean isCreate, @RequestParam boolean isUpdate) {
        try {
            if(event == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveEvent(event, isCreate, isUpdate);
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
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            event = new Event();
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
}
