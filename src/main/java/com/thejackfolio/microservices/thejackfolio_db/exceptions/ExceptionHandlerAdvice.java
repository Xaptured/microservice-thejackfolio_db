package com.thejackfolio.microservices.thejackfolio_db.exceptions;

import com.thejackfolio.microservices.thejackfolio_db.models.ExceptionBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(LANDataBaseException.class)
    public ResponseEntity<ExceptionBody> handleLANDataBaseException(LANDataBaseException exception) {
        LOGGER.error("Exception occurred during database operation", exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }

    @ExceptionHandler(LANMapperException.class)
    public ResponseEntity<ExceptionBody> handleLANMapperException(LANMapperException exception) {
        LOGGER.error("Exception occurred during mapper operation", exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleResourceNotFoundException(ResourceNotFoundException exception) {
        LOGGER.error("Exception occurred due to resource not found", exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionBody);
    }
}
