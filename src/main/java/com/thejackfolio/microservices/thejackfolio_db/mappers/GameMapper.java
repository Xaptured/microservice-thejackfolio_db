/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Games;
import com.thejackfolio.microservices.thejackfolio_db.entities.InterestedGames;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Game;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameMapper.class);

    public Games modelToEntityGame(Game game) throws MapperException {
        Games gameEntity = null;
        try {
            if(game != null) {
                gameEntity = new Games();
                gameEntity.setName(game.getName());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return gameEntity;
    }

    public List<InterestedGames> modelToEntityGames(Game[] games, String email) throws MapperException {
        List<InterestedGames> interestedGames = new ArrayList<>();
        try {
            for(Game game : games) {
                InterestedGames interestedGame = new InterestedGames();
                interestedGame.setEmail(email);
                interestedGame.setGameName(game.getName());
                interestedGames.add(interestedGame);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return interestedGames;
    }
}
