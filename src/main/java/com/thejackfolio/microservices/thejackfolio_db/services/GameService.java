/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.Games;
import com.thejackfolio.microservices.thejackfolio_db.entities.InterestedGames;
import com.thejackfolio.microservices.thejackfolio_db.entities.ProfileDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.GameMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Game;
import com.thejackfolio.microservices.thejackfolio_db.models.ProfileDetail;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.GameServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameMapper mapper;
    @Autowired
    private GameServiceHelper helper;

    public void saveGame(Game game) throws MapperException, DataBaseOperationException {
        Games gameEntity = mapper.modelToEntityGame(game);
        helper.saveGame(gameEntity);
    }

    public List<InterestedGames> saveInterestedGames(ProfileDetail detail) throws MapperException, DataBaseOperationException {
        List<InterestedGames> interestedGames = mapper.modelToEntityGames(detail.getInterestedGames(), detail.getEmail());
        if(!interestedGames.isEmpty()) {
            helper.saveInterestedGames(interestedGames);
        }
        return interestedGames;
    }

    public List<InterestedGames> updateInterestedGames(ProfileDetail detail) throws MapperException, DataBaseOperationException {
        List<InterestedGames> games = helper.findInterestedGamesByEmail(detail.getEmail());
        List<InterestedGames> interestedGames = mapper.modelToEntityGames(detail.getInterestedGames(), games, detail.getEmail());
        if(!interestedGames.isEmpty()) {
            helper.saveInterestedGames(interestedGames);
        }
        List<InterestedGames> interestedGamesToDelete = mapper.modelToEntityGamesToRemove(detail.getInterestedGames(), games);
        if(!interestedGamesToDelete.isEmpty()) {
            helper.deleteInterestedGames(interestedGamesToDelete);
        }
        return interestedGames;
    }

    public Integer getGameId(String gameName) throws DataBaseOperationException {
        return helper.getGameId(gameName);
    }

    public String getGameName(Integer gameId) throws DataBaseOperationException {
        return helper.getGameName(gameId);
    }
}
