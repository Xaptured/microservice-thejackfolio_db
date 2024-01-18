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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Game", description = "Game management APIs")
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService service;

    @Operation(
            summary = "Save or Update game",
            description = "Save or Update game and gives the same game response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-or-update-game")
    public ResponseEntity<Game> saveOrUpdateGame(@RequestBody Game game) {
        try {
            if(game == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveOrUpdateGame(game);
            game.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            game.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(game);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @Operation(
            summary = "Get all active games",
            description = "Get all active games with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-active-games")
    public ResponseEntity<List<Game>> findAllActiveGames() {
        List<Game> activeGameModels = null;
        try {
            activeGameModels = service.findAllActiveGames();
            if(activeGameModels != null) {
                activeGameModels.get(0).setMessage(StringConstants.REQUEST_PROCESSED);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            activeGameModels = new ArrayList<>();
            Game game = new Game();
            game.setMessage(exception.getMessage());
            activeGameModels.add(game);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(activeGameModels);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(activeGameModels);
    }
}
