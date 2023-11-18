/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Game;
import com.thejackfolio.microservices.thejackfolio_db.services.GameService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Game", description = "Game management APIs")
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService service;

    @Operation(
            summary = "Save game",
            description = "Save game and gives the same game response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-game")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        try {
            if(game == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveGame(game);
            game.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            game.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(game);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}
