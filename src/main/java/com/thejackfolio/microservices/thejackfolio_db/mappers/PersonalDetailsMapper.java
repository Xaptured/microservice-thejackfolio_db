/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.PersonalDetails;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Address;
import com.thejackfolio.microservices.thejackfolio_db.models.Education;
import com.thejackfolio.microservices.thejackfolio_db.models.Link;
import com.thejackfolio.microservices.thejackfolio_db.models.Name;
import com.thejackfolio.microservices.thejackfolio_db.utilities.EncryptDecrypt;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PersonalDetailsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalDetailsMapper.class);
    @Autowired
    private EncryptDecrypt encryptDecrypt;

    public PersonalDetails modelToEntity(com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails personalDetails) throws MapperException {
        PersonalDetails details = null;
        try {
            if (personalDetails != null) {
                details = new PersonalDetails();
                if (personalDetails.getName() != null) {
                    details.setFirstName(encryptDecrypt.encrypt(personalDetails.getName().getFirstName()));
                    details.setMiddleName(encryptDecrypt.encrypt(personalDetails.getName().getMiddleName()));
                    details.setLastName(encryptDecrypt.encrypt(personalDetails.getName().getLastName()));
                }
                details.setEmail(personalDetails.getEmail());
                details.setDateOfBirth(encryptDecrypt.encrypt(personalDetails.getDateOfBirth().toString()));
                if (personalDetails.getAddress() != null) {
                    details.setFlatNumber(personalDetails.getAddress().getFlatNumber());
                    details.setSociety(personalDetails.getAddress().getSociety());
                    details.setCity(personalDetails.getAddress().getCity());
                    details.setPinCode(personalDetails.getAddress().getPinCode());
                    details.setState(personalDetails.getAddress().getstate());
                    details.setCountry(personalDetails.getAddress().getCountry());
                }
                details.setPhoneNumber(personalDetails.getPhoneNumber());
                if (personalDetails.getEducation() != null) {
                    details.setCollegeName(personalDetails.getEducation().getCollegeName());
                    details.setBranch(personalDetails.getEducation().getBranch());
                    details.setFromDate(personalDetails.getEducation().getFromDate());
                    details.setToDate(personalDetails.getEducation().getToDate());
                }
                if (personalDetails.getLink() != null) {
                    details.setLinkedinLink(personalDetails.getLink().getLinkedinLink());
                    details.setGithubLink(personalDetails.getLink().getGithubLink());
                    details.setYoutubeLink(personalDetails.getLink().getYoutubeLink());
                    details.setInstagramLink(personalDetails.getLink().getInstagramLink());
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return details;
    }

    public com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails entityToModel(PersonalDetails personalDetails) throws MapperException {
        com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails details = null;
        try {
            if (personalDetails != null) {
                details = new com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails();
                Name name = new Name();
                name.setFirstName(encryptDecrypt.decrypt(personalDetails.getFirstName()));
                name.setMiddleName(encryptDecrypt.decrypt(personalDetails.getMiddleName()));
                name.setLastName(encryptDecrypt.decrypt(personalDetails.getLastName()));
                details.setName(name);
                details.setEmail(personalDetails.getEmail());
                details.setDateOfBirth(Date.valueOf(encryptDecrypt.decrypt(personalDetails.getDateOfBirth())));
                Address address = new Address();
                address.setFlatNumber(personalDetails.getFlatNumber());
                address.setSociety(personalDetails.getSociety());
                address.setCity(personalDetails.getCity());
                address.setstate(personalDetails.getState());
                address.setCountry(personalDetails.getCountry());
                address.setPinCode(personalDetails.getPinCode());
                details.setAddress(address);
                details.setPhoneNumber(personalDetails.getPhoneNumber());
                Education education = new Education();
                education.setCollegeName(personalDetails.getCollegeName());
                education.setBranch(personalDetails.getBranch());
                education.setFromDate(personalDetails.getFromDate());
                education.setToDate(personalDetails.getToDate());
                details.setEducation(education);
                Link link = new Link();
                link.setLinkedinLink(personalDetails.getLinkedinLink());
                link.setGithubLink(personalDetails.getGithubLink());
                link.setYoutubeLink(personalDetails.getYoutubeLink());
                link.setInstagramLink(personalDetails.getInstagramLink());
                details.setLink(link);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR, exception);
        }
        return details;
    }
}
