/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.*;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EmailException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.ClientsMapper;
import com.thejackfolio.microservices.thejackfolio_db.mappers.GameMapper;
import com.thejackfolio.microservices.thejackfolio_db.mappers.LANEventMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.models.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.ClientServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.GameServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.LANEventServiceHelper;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientsMapper mapper;
    @Autowired
    private ClientServiceHelper helper;
    @Autowired
    private GameService service;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private GameServiceHelper gameServiceHelper;
    @Autowired
    private EventService eventService;
    @Autowired
    private LANEventServiceHelper lanEventServiceHelper;
    @Autowired
    private LANEventMapper lanEventMapper;

    public void saveComments(ClientComments clientComments) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = mapper.modelToEntityComments(clientComments);
        helper.saveClientComments(commentEntity);
    }

    public void updateComments(ClientComments clientComments, Integer commentId) throws MapperException, DataBaseOperationException {
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = mapper.modelToEntityComments(clientComments, commentId);
        helper.saveClientComments(commentEntity);
    }

    public List<ClientComments> getComments() throws MapperException, DataBaseOperationException {
        List<ClientComments> comments = null;
        List<com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments> commentEntities = helper.findNonRepliedComments();
        comments = mapper.entityToModel(commentEntities);
        return comments;
    }

    public ClientComments findCommentById(Integer commentId) throws DataBaseOperationException, MapperException {
        ClientComments comment = null;
        com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments commentEntity = helper.findCommentById(commentId);
        comment = mapper.entityToModelForComment(commentEntity);
        return comment;
    }

    public void saveCredentials(ClientCredential credential) throws MapperException, DataBaseOperationException, EmailException {
        ClientCredentials credentialEntity = helper.findCredentialByEmail((credential.getEmail()));
        if(credentialEntity == null) {
            credentialEntity = mapper.modelToEntityCredential(credential);
            helper.saveCredentials(credentialEntity);
            ProfileDetail detail = new ProfileDetail();
            detail.setEmail(credential.getEmail());
            saveOrUpdateProfile(detail);

            LocalDate today = LocalDate.now();
            Date sqlDate = Date.valueOf(today);
            Feedback feedback = new Feedback(sqlDate.toString(),false, credential.getEmail()  );
            FeedbackEntity feedbackEntity = lanEventMapper.convertFeedbackModelToEntity(feedback);
            lanEventServiceHelper.saveFeedback(feedbackEntity);
        } else {
            throw new EmailException(StringConstants.DUPLICATE_EMAIL);
        }

    }

    public ClientCredential findClientCredential(String email) throws DataBaseOperationException, MapperException {
        ClientCredential credential = null;
        ClientCredentials credentialEntity = helper.findCredentialByEmail(email);
        credential = mapper.entityToModelCredential(credentialEntity);
        return credential;
    }

    public EmailValidationDetails findClientId(String email) throws DataBaseOperationException, MapperException {
        EmailValidationDetails details = new EmailValidationDetails();
        ClientCredentials credentialEntity = helper.findCredentialByEmail(email);
        details.setClientId(credentialEntity.getId());
        details.setSecretCode(credentialEntity.getCode());
        return details;
    }

    public void verifyClientAccount(Integer clientId) throws DataBaseOperationException {
        helper.verifyClientAccount(clientId);
    }

    public void saveSecretCode(EmailValidationDetails details) throws DataBaseOperationException {
        helper.saveSecretCode(details);
    }

    public EmailValidationDetails findDetailsById(Integer id) throws DataBaseOperationException {
        return helper.findDetailsById(id);
    }

    public ProfileDetail saveOrUpdateProfile(ProfileDetail detail) throws MapperException, DataBaseOperationException {
        ProfileDetails details = helper.findDetailsByEmail(detail.getEmail());
        if(details == null) {
            details = mapper.modelToEntityDetails(detail);
            helper.saveProfile(details);
            if(detail.getInterestedGames() != null) {
                List<InterestedGames> interestedGames = service.saveInterestedGames(detail);
                List<InterestedGame> games = gameMapper.entityToModelGames(interestedGames);
                detail.setInterestedGames(games);
            }
        } else {
            details = mapper.modelToEntityDetails(detail, details);
            helper.saveProfile(details);
            List<InterestedGames> interestedGames = service.updateInterestedGames(detail);
            List<InterestedGame> games = gameMapper.entityToModelGames(interestedGames);
            detail.setInterestedGames(games);
        }
        return detail;
    }

    public ProfileDetail getProfileDetails(String email) throws DataBaseOperationException, MapperException {
        ProfileDetail detail = null;
        ProfileDetails details = helper.findDetailsByEmail(email);
        if(details != null) {
            List<InterestedGames> games = gameServiceHelper.findInterestedGamesByEmail(details.getEmail());
            detail = mapper.entityToModelDetails(details, games);
        }
        return detail;
    }

    public List<ProfileDetail> getProfileDetails(List<TeamDetail> teamDetails) throws DataBaseOperationException, MapperException {
        List<ProfileDetail> details = null;
        if(teamDetails != null) {
            details = new ArrayList<>();
            for(TeamDetail detail : teamDetails) {
                ProfileDetail profileDetail = getProfileDetails(detail.getEmail());
                details.add(profileDetail);
            }
        }
        return details;
    }

    public void saveOrUpdatePartner(Partner partner) throws DataBaseOperationException, MapperException, IOException {
        Partners partnerEntity = helper.findPartnerByEmail(partner.getEmail());
        if(partnerEntity == null) {
            partnerEntity = mapper.modelToEntityPartner(partner);
            helper.savePartner(partnerEntity);
        } else {
            boolean isFilesDeleted = deleteDocumentsFromPath(partnerEntity.getLogoPath(), partnerEntity.getDocumentPath(), partner);
            if( isFilesDeleted ) {
                partnerEntity = mapper.modelToEntityPartner(partner, partnerEntity);
                helper.savePartner(partnerEntity);
            }
            else {
                throw new FileNotFoundException(StringConstants.FILE_NOT_PRESENT);
            }
        }
    }

    public Partner saveDocuments(MultipartFile image, MultipartFile doc, String email) throws DataBaseOperationException, MapperException, IOException {
        Partner partner = null;
        Partners partnerEntity = helper.findPartnerByEmail(email);
        if(partnerEntity != null) {
            partnerEntity = mapper.modelToEntityPartner(partnerEntity, image, doc);
            helper.savePartner(partnerEntity);
            saveDocumentsToPath(partnerEntity.getLogoPath(), partnerEntity.getDocumentPath(), image, doc);
            partner = mapper.entityToModelPartner(partnerEntity);
        }
        return partner;
    }

    private void saveDocumentsToPath(String logoPath, String docPath, MultipartFile image, MultipartFile document) throws IOException {
        image.transferTo(new File(logoPath).toPath());
        document.transferTo(new File(docPath).toPath());
    }

    private boolean deleteDocumentsFromPath(String logoPath, String docPath, Partner partner) {
        File logoFile = new File(logoPath);
        File docFile = new File(docPath);
        boolean isFilesDeleted = false;
        if(logoFile.exists() && docFile.exists()) {
            logoFile.delete();
            docFile.delete();
            return true;
        } else {
            return false;
        }
    }

    public Partner findPartnerByEmail(String email) throws DataBaseOperationException, MapperException {
        Partners partnerEntity = helper.findPartnerByEmail(email);
        Partner partner = mapper.entityToModelPartner(partnerEntity);
        return partner;
    }

    public byte[] findLogoByEmail(String email) throws DataBaseOperationException, IOException {
        Partners partnerEntity = helper.findPartnerByEmail(email);
        if(partnerEntity != null) {
            String logoPath = partnerEntity.getLogoPath();
            byte[] logo = Files.readAllBytes(new File(logoPath).toPath());
            return logo;
        } else {
            return null;
        }
    }

    public byte[] findDocumentByEmail(String email) throws DataBaseOperationException, IOException {
        Partners partnerEntity = helper.findPartnerByEmail(email);
        if(partnerEntity != null) {
            String docPath = partnerEntity.getDocumentPath();
            byte[] document = Files.readAllBytes(new File(docPath).toPath());
            return document;
        } else {
            return null;
        }
    }

    public void saveJoiner(Joiner joiner) throws MapperException, DataBaseOperationException, EmailException {
        Joiners joinerEntity = helper.findJoiner(joiner.getEmail());
        if (joinerEntity == null) {
            joinerEntity = mapper.modelToEntityJoiner(joiner);
            helper.saveJoiner(joinerEntity);
        } else {
            throw new EmailException(StringConstants.DUPLICATE_EMAIL);
        }
    }

    public void updateJoiner(String email) throws DataBaseOperationException, EmailException {
        Joiners joinerEntity = helper.findJoiner(email);
        if (joinerEntity == null) {
            throw new EmailException(StringConstants.EMAIL_NOT_PRESENT);
        } else {
            joinerEntity.setOnboarded(true);
            joinerEntity.setJoiningDate(LocalDate.now());
            helper.saveJoiner(joinerEntity);
        }
    }
}
