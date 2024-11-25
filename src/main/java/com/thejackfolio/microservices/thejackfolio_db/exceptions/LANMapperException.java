/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.exceptions;

public class LANMapperException extends RuntimeException{

    public LANMapperException(String message) {
        super(message);
    }

    public LANMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
