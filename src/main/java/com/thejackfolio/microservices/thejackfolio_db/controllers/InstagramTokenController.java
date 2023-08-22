/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Instagram_Token;
import com.thejackfolio.microservices.thejackfolio_db.services.InstagramTokenService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Instagram-token", description = "Instagram Token APIs")
@RestController
@RequestMapping("/instagram-token")
public class InstagramTokenController {

    @Autowired
    private InstagramTokenService service;

    @Operation(
            summary = "Save token",
            description = "Save token and gives the same token response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-token")
    public ResponseEntity<Instagram_Token> saveInstagramToken(@RequestBody Instagram_Token instagramToken){
        try{
            if(instagramToken == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveInstagramToken(instagramToken);
            instagramToken.setMessage(StringConstants.REQUEST_PROCESSED);
        }  catch (MapperException | DataBaseOperationException exception){
            instagramToken.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(instagramToken);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(instagramToken);
    }

    @Operation(
            summary = "Get token",
            description = "Gets token with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-token")
    public ResponseEntity<Instagram_Token> getInstagramToken(){
        Instagram_Token instagramToken = null;
        try{
            instagramToken = service.getInstagramToken();
            instagramToken.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception){
            instagramToken = new Instagram_Token();
            instagramToken.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(instagramToken);
        }
        return ResponseEntity.status(HttpStatus.OK).body(instagramToken);
    }
}
