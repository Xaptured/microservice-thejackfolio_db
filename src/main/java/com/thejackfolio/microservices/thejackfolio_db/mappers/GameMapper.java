/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Games;
import com.thejackfolio.microservices.thejackfolio_db.entities.InterestedGames;
import com.thejackfolio.microservices.thejackfolio_db.enums.GameStatus;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Game;
import com.thejackfolio.microservices.thejackfolio_db.models.InterestedGame;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameMapper.class);

    public Games modelToEntityGame(Game game) throws MapperException {
        Games gameEntity = null;
        try {
            if(game != null) {
                gameEntity = new Games();
                gameEntity.setName(game.getName());
                gameEntity.setStatus(GameStatus.PENDING);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return gameEntity;
    }

    public List<Game> entityToModelGameList(List<Games> gameEntities) throws MapperException {
        List<Game> gameModels = null;
        try {
            if(gameEntities != null && !gameEntities.isEmpty()) {
                gameModels = new ArrayList<>();
                for(Games entity : gameEntities) {
                    Game game = new Game();
                    game.setName(entity.getName());
                    gameModels.add(game);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return gameModels;
    }

    public List<InterestedGames> modelToEntityGames(List<InterestedGame> games, String email) throws MapperException {
        List<InterestedGames> interestedGames = new ArrayList<>();
        try {
            int counter = 1;
            for(InterestedGame game : games) {
                InterestedGames interestedGame = new InterestedGames();
                interestedGame.setEmail(email);
                interestedGame.setGameName(game.getGameName());
                interestedGame.setGameNumber(counter++);
                interestedGames.add(interestedGame);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return interestedGames;
    }

    public List<InterestedGames> modelToEntityGames(List<InterestedGame> games, List<InterestedGames> interestedGames, String email) throws MapperException {
        List<InterestedGames> gameEntities = new ArrayList<>();
        try {
            int counter = findMaxGameNumber(interestedGames) + 1;
            for(InterestedGame game : games) {
                InterestedGames entity;
                if(game.getGameNumber() == null) {
                    entity = new InterestedGames();
                    entity.setEmail(email);
                    entity.setGameName(game.getGameName());
                    entity.setGameNumber(counter++);
                    gameEntities.add(entity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return gameEntities;
    }

    public List<InterestedGames> modelToEntityGamesToRemove(List<InterestedGame> games, List<InterestedGames> interestedGames) throws MapperException {
        List<InterestedGames> gameEntities = new ArrayList<>();
        try {
            for(InterestedGame game : games) {
                InterestedGames entity;
                if(game.getGameNumber() != null) {
                    entity = searchInterestedGameByGameNumber(interestedGames, game.getGameNumber());
                    gameEntities.add(entity);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return gameEntities;
    }

    private Integer findMaxGameNumber(List<InterestedGames> games) {
        int maxGameNumber = 0;
        Optional<InterestedGames> maxNumber = games.stream().max(Comparator.comparingInt(InterestedGames::getGameNumber));
        if(maxNumber.isPresent()) {
            maxGameNumber = maxNumber.get().getGameNumber();
        }
        return maxGameNumber;
    }

    private InterestedGames searchInterestedGameByGameNumber(List<InterestedGames> games, Integer gameNumber) {
        for(InterestedGames interestedGame: games) {
            if(Objects.equals(interestedGame.getGameNumber(), gameNumber)) {
                return interestedGame;
            }
        }
        return null;
    }

    public List<InterestedGame> entityToModelGames(List<InterestedGames> games) throws MapperException {
        List<InterestedGame> interestedGames = null;
        try {
            if(games != null && !games.isEmpty()) {
                interestedGames = new ArrayList<>();
                for(InterestedGames game : games) {
                    InterestedGame interestedGame = new InterestedGame();
                    interestedGame.setEmail(game.getEmail());
                    interestedGame.setGameName(game.getGameName());
                    interestedGame.setGameNumber(game.getGameNumber());
                    interestedGames.add(interestedGame);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return interestedGames;
    }
}
