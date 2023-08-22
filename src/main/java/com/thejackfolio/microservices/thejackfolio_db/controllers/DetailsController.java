/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Details;
import com.thejackfolio.microservices.thejackfolio_db.services.DetailsService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Detail", description = "Detail management APIs")
@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @Operation(
            summary = "Save details",
            description = "Save details which includes both personal and professional and gives the same details response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-details")
    public ResponseEntity<Details> saveDetails(@RequestBody Details details) {
        try {
            if (details == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            detailsService.saveDetails(details);
            details.setResponseMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            details.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(details);
    }

    @Operation(
            summary = "Get details",
            description = "It gives the details as response which includes both personal and professional with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-details")
    public ResponseEntity<Details> getDetails() {
        Details details = null;
        try {
            details = detailsService.getDetails();
            details.setResponseMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            details = new Details();
            details.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
        }
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
}
