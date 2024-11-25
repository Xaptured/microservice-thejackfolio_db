/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.exceptions;

public class LANDataBaseException extends RuntimeException{
    public LANDataBaseException(String message) {
        super(message);
    }

    public LANDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
