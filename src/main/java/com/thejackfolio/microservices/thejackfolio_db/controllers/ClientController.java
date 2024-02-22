/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Partners;
import com.thejackfolio.microservices.thejackfolio_db.entities.ProfileDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.repositories.PartnersRepository;
import com.thejackfolio.microservices.thejackfolio_db.services.ClientService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @Operation(
            summary = "Get client id",
            description = "Get client id"
    )
    @GetMapping("/get-client-id/{email}")
    public ResponseEntity<EmailValidationDetails> findClientId(@PathVariable String email) {
        EmailValidationDetails details = null;
        try {
            details = clientService.findClientId(email);
        } catch (DataBaseOperationException | MapperException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

    @Operation(
            summary = "Get client id",
            description = "Get client id"
    )
    @PostMapping("/verify-account/{clientId}")
    public ResponseEntity<String> verifyClientAccount(@PathVariable Integer clientId) {
        try {
            clientService.verifyClientAccount(clientId);
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(StringConstants.REQUEST_PROCESSED);
    }

    @Operation(
            summary = "Save secret code",
            description = "Save secret code"
    )
    @PostMapping("/save-secret-code")
    public ResponseEntity<String> saveSecretCode(@RequestBody EmailValidationDetails details) {
        try {
            clientService.saveSecretCode(details);
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(StringConstants.REQUEST_PROCESSED);
    }

    @Operation(
            summary = "Get Email Validation Details",
            description = "Get Email Validation Details"
    )
    @PostMapping("/save-secret-code/{id}")
    public ResponseEntity<EmailValidationDetails> findDetailsById(@PathVariable Integer id) {
        EmailValidationDetails details = null;
        try {
            details = clientService.findDetailsById(id);
        } catch (DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

    @Operation(
            summary = "Save profile",
            description = "Save profile and gives the same profile response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-profile")
    public ResponseEntity<ProfileDetail> saveOrUpdateProfileDetails(@RequestBody ProfileDetail details) {
        try {
            if (details == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            details = clientService.saveOrUpdateProfile(details);
            details.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            details.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(details);
    }

    @Operation(
            summary = "Get profile",
            description = "Get profile and gives the same profile response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-profile/{email}")
    public ResponseEntity<ProfileDetail> getProfileDetails(@PathVariable String email) {
        ProfileDetail detail = null;
        try {
            if (email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            detail = clientService.getProfileDetails(email);
            if(detail == null) {
                detail = new ProfileDetail();
                detail.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detail);
            } else {
                detail.setMessage(StringConstants.REQUEST_PROCESSED);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            detail = new ProfileDetail();
            detail.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detail);
        }
        return ResponseEntity.status(HttpStatus.OK).body(detail);
    }

    @Operation(
            summary = "Is profile present",
            description = "Is profile present"
    )
    @GetMapping("/is-profile-present/{email}")
    public ResponseEntity<Boolean> isProfilePresent(@PathVariable String email) {
        boolean response = false;
        try {
            if (email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ProfileDetail detail = clientService.getProfileDetails(email);
            if(detail != null) {
                response = true;
            }
        } catch (MapperException | DataBaseOperationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Save partner",
            description = "Save partner and gives the same partner response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-partner")
    public ResponseEntity<Partner> saveOrUpdatePartner(@RequestBody Partner partner) {
        try {
            if(partner == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            clientService.saveOrUpdatePartner(partner);
            partner.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException | IOException exception) {
            partner.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(partner);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(partner);
    }

    @Operation(
            summary = "Get partner",
            description = "Get partner and gives the response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-partner/{email}")
    public ResponseEntity<Partner> findPartner(@PathVariable String email) {
        Partner partner = null;
        try {
            if(email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            partner = clientService.findPartnerByEmail(email);
            if(partner == null) {
                partner = new Partner();
                partner.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(partner);
            } else {
                partner.setMessage(StringConstants.REQUEST_PROCESSED);
            }
        } catch (MapperException | DataBaseOperationException exception) {
            partner.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(partner);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(partner);
    }

    @Operation(
            summary = "Get logo",
            description = "Get logo and gives the response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-logo/{email}")
    public ResponseEntity<Document> findLogo(@PathVariable String email) {
        Document document = new Document();
        try {
            if(email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            byte[] logo = clientService.findLogoByEmail(email);
            if(logo == null) {
                document.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(document);
            }
            document.setDocument(logo);
            document.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (DataBaseOperationException | IOException exception) {
            document.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(document);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(document);
    }

    @Operation(
            summary = "Get document",
            description = "Get document and gives the response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-document/{email}")
    public ResponseEntity<Document> findDoc(@PathVariable String email) {
        Document document = new Document();
        try {
            if(email == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            byte[] doc = clientService.findDocumentByEmail(email);
            if(doc == null) {
                document.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(document);
            }
            document.setDocument(doc);
            document.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (DataBaseOperationException | IOException exception) {
            document.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(document);
        }
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    @Operation(
            summary = "Save documents",
            description = "Save documents and gives the same documents response with a message which defines whether the request is successful or not."
    )
    @RequestMapping(path = "/save-documents/{email}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Partner> saveDocuments(@RequestPart MultipartFile image, @RequestPart MultipartFile doc, @PathVariable String email) {
        Partner partner = null;
        try {
            if(image.isEmpty() || doc.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            partner = clientService.saveDocuments(image, doc, email);
            if(partner == null) {
                partner = new Partner();
                partner.setMessage(StringConstants.EMAIL_NOT_PRESENT);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(partner);
            }
            partner.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (DataBaseOperationException | IOException | MapperException exception) {
            partner = new Partner();
            partner.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(partner);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(partner);
    }
}
