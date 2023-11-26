/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.Coins;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.CoinsMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Coin;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.CoinsServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    @Autowired
    private CoinsMapper mapper;
    @Autowired
    private CoinsServiceHelper helper;

    public void saveOrUpdateCoins(Coin coin) throws MapperException, DataBaseOperationException {
        Coins coins = helper.findCoinsByEmail(coin.getEmail());
        if(coins == null) {
            coins = mapper.modelToEntity(coin);
        }
        else {
            coins.setBalance(coin.getBalance());
        }
        helper.saveCoins(coins);
    }

    public Coin getCoins(String email) throws DataBaseOperationException, MapperException {
        Coins coins = helper.findCoinsByEmail(email);
        Coin coin = mapper.entityToModel(coins);
        return coin;
    }
}
