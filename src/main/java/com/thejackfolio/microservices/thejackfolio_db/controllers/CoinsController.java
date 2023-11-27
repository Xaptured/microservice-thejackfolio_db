/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Coin;
import com.thejackfolio.microservices.thejackfolio_db.services.CoinService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coin", description = "Coin management APIs")
@RestController
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private CoinService service;

    @Operation(
            summary = "Save OR Update coins",
            description = "Save or Update coins and gives the same coins response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-coins")
    public ResponseEntity<Coin> saveOrUpdateCoins(@RequestBody Coin coin) {
        try {
            if(coin == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveOrUpdateCoins(coin);
            coin.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            coin.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(coin);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(coin);
    }

    @Operation(
            summary = "Get coins",
            description = "Get coins with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-coins/{email}")
    public ResponseEntity<Coin> getCoins(@PathVariable String email) {
        Coin coin = null;
        try {
            if(email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            coin = service.getCoins(email);
            if(coin.getMessage().equals(StringConstants.EMAIL_NOT_PRESENT)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(coin);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            coin.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(coin);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(coin);
    }
}
