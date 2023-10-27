/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.models.ClientCredential;
import com.thejackfolio.microservices.thejackfolio_db.services.ClientService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Client", description = "Client management APIs")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(
            summary = "Save comments",
            description = "Save comments and gives the same comments response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-comments")
    public ResponseEntity<ClientComments> saveComments(@RequestBody ClientComments comments) {
        try {
            if (comments == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            clientService.saveComments(comments);
            comments.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            comments.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(comments);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @Operation(
            summary = "Update comment",
            description = "Update comment and gives the same comments response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/update-comments/{commentId}")
    public ResponseEntity<ClientComments> updateComments(@RequestBody ClientComments comments, @PathVariable Integer commentId) {
        try{
            if(comments == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            clientService.updateComments(comments, commentId);
            comments.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            comments.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(comments);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @Operation(
            summary = "Get comments",
            description = "It gives the list of comments as response which are not replied yet with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-comments")
    public ResponseEntity<List<ClientComments>> getComments() {
        List<ClientComments> comments = null;
        try {
            comments = clientService.getComments();
        } catch (MapperException | DataBaseOperationException exception) {
            comments = new ArrayList<>();
            ClientComments comment = new ClientComments();
            comment.setMessage(exception.getMessage());
            comments.add(comment);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(comments);
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @Operation(
            summary = "Get comment",
            description = "Get comment and gives the same comment response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-comments/{commentId}")
    public ResponseEntity<ClientComments> getCommentById(@PathVariable Integer commentId){
        ClientComments comment = null;
        try{
            comment = clientService.findCommentById(commentId);
            if(comment == null){
                comment = new ClientComments();
                comment.setMessage(StringConstants.ID_NOT_PRESENT);
            } else {
                comment.setMessage(StringConstants.REQUEST_PROCESSED);
            }
        } catch (DataBaseOperationException | MapperException exception){
            comment = new ClientComments();
            comment.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(comment);
        }
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @Operation(
            summary = "Save credential",
            description = "Save credential and gives the same credential response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-credentials")
    public ResponseEntity<ClientCredential> saveClientCredential(@RequestBody  ClientCredential credential) {
        try {
            if (credential == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            clientService.saveCredentials(credential);
            credential.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            credential.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(credential);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(credential);
    }

    @Operation(
            summary = "Get credential",
            description = "Get credential and gives the same credential response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-credentials/{email}")
    public ResponseEntity<ClientCredential> findClientCredential(@PathVariable String email) {
        ClientCredential credential = null;
        try{
            credential = clientService.findClientCredential(email);
            if(credential == null){
                credential = new ClientCredential();
                credential.setMessage(StringConstants.EMAIL_NOT_PRESENT);
            } else {
                credential.setMessage(StringConstants.REQUEST_PROCESSED);
            }
        } catch (DataBaseOperationException | MapperException exception){
            credential = new ClientCredential();
            credential.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(credential);
        }
        return ResponseEntity.status(HttpStatus.OK).body(credential);
    }
}
