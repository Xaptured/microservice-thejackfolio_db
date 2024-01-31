/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Games;
import com.thejackfolio.microservices.thejackfolio_db.entities.InterestedGames;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.models.Game;
import com.thejackfolio.microservices.thejackfolio_db.repositories.GamesRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.InterestedGamesRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceHelper.class);

    @Autowired
    private GamesRepository repository;
    @Autowired
    private InterestedGamesRepository interestedGamesRepository;

    public void saveGame(Games games) throws DataBaseOperationException {
        try {
            repository.save(games);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<Games> findAllActiveGames() throws DataBaseOperationException {
        List<Games> activeGames = null;
        try {
            activeGames = repository.findAllActiveGames();
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return activeGames;
    }

    public Games findByGameName(String gameName) throws DataBaseOperationException {
        Games gameEntity = null;
        try {
            gameEntity = repository.findByName(gameName).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return gameEntity;
    }

    public List<Games> findAllByGameNames(List<String> gameNames) throws DataBaseOperationException {
        List<Games> gameEntities = null;
        try {
            gameEntities = repository.findAllByNames(gameNames).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return gameEntities;
    }

    public void saveInterestedGames(List<InterestedGames> games) throws DataBaseOperationException {
        try {
            interestedGamesRepository.saveAll(games);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void deleteInterestedGames(List<InterestedGames> games) throws DataBaseOperationException {
        try {
            interestedGamesRepository.deleteAll(games);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<InterestedGames> findInterestedGamesByEmail(String email) throws DataBaseOperationException {
        List<InterestedGames> games = null;
        try {
            games = interestedGamesRepository.findByEmail(email).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return games;
    }

    public Integer getGameId(String gameName) throws DataBaseOperationException {
        Games game = null;
        try {
            game = repository.findByName(gameName).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return game == null ? null : game.getId();
    }

    public String getGameName(Integer gameId) throws DataBaseOperationException {
        Games game = null;
        try {
            game = repository.findById(gameId).orElse(null);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return game == null ? null : game.getName();
    }
}
