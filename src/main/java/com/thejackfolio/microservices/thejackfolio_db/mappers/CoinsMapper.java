/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Coins;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Coin;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CoinsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinsMapper.class);

    public Coins modelToEntity(Coin coin) throws MapperException {
        Coins coins = null;
        try {
            if(coin != null) {
                coins = new Coins();
                coins.setEmail(coin.getEmail());
                coins.setBalance(coin.getBalance());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return  coins;
    }

    public Coin entityToModel(Coins coins) throws MapperException {
        Coin coin = null;
        try {
            if(coins != null) {
                coin = new Coin();
                coin.setBalance(coins.getBalance());
                coin.setEmail(coins.getEmail());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return coin;
    }
}
