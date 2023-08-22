/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Details;
import com.thejackfolio.microservices.thejackfolio_db.models.PersonalDetails;
import com.thejackfolio.microservices.thejackfolio_db.models.ProfessionalDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {

    @Autowired
    private PersonalDetailsService personalDetailsService;
    @Autowired
    private ProfessionalDetailsService professionalDetailsService;

    public void saveDetails(Details details) throws MapperException, DataBaseOperationException {
        personalDetailsService.savePersonalDetails(details.getPersonalDetails());
        professionalDetailsService.saveProfessionalDetails(details.getProfessionalDetails());
    }

    public Details getDetails() throws MapperException, DataBaseOperationException {
        Details details = null;
        PersonalDetails personalDetails = personalDetailsService.getPersonalDetails();
        List<ProfessionalDetail> professionalDetails = professionalDetailsService.getProfessionalDetails();
        if (personalDetails != null && professionalDetails != null) {
            details = new Details();
            details.setPersonalDetails(personalDetails);
            details.setProfessionalDetails(professionalDetails);
        }
        return details;
    }
}
