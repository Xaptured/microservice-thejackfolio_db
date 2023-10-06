/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.services;

import com.thejackfolio.microservices.thejackfolio_db.entities.Organizations;
import com.thejackfolio.microservices.thejackfolio_db.entities.Projects;
import com.thejackfolio.microservices.thejackfolio_db.entities.Roles;
import com.thejackfolio.microservices.thejackfolio_db.entities.TechStacks;
import com.thejackfolio.microservices.thejackfolio_db.enums.TechStack;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.mappers.ProfessionalDetailsMapper;
import com.thejackfolio.microservices.thejackfolio_db.models.Organization;
import com.thejackfolio.microservices.thejackfolio_db.models.ProfessionalDetail;
import com.thejackfolio.microservices.thejackfolio_db.models.Project;
import com.thejackfolio.microservices.thejackfolio_db.servicehelpers.ProfessionalDetailsServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionalDetailsService {

    @Autowired
    private ProfessionalDetailsMapper mapper;
    @Autowired
    private ProfessionalDetailsServiceHelper helper;

    public void saveProfessionalDetails(List<ProfessionalDetail> professionalDetails) throws MapperException, DataBaseOperationException {
        if (professionalDetails != null && !professionalDetails.isEmpty()) {
            for (ProfessionalDetail professionalDetail : professionalDetails) {
                Organizations organizationEntity = mapper.modelToEntityOrganizations(professionalDetail);
                organizationEntity = helper.saveOrganization(organizationEntity);

                for (Project project : professionalDetail.getProjects()) {
                    Projects projectEntity = mapper.modelToEntityProjects(project, organizationEntity.getId());
                    projectEntity = helper.saveProject(projectEntity);

                    for (TechStack techStack : project.getTechStacks()) {
                        TechStacks techStackEntity = mapper.modelToEntityTechStacks(techStack, projectEntity.getId());
                        helper.saveTechStack(techStackEntity);
                    }

                    for (String role : project.getRoles()) {
                        Roles roleEntity = mapper.modelToEntityRoles(role, projectEntity.getId());
                        helper.saveRole(roleEntity);
                    }
                }
            }
        }
    }

    public List<ProfessionalDetail> getProfessionalDetails() throws MapperException, DataBaseOperationException {
        List<ProfessionalDetail> professionalDetails = new ArrayList<>();
        List<Organizations> organizationEntities = helper.findAllOrganizations();
        for (Organizations organizationEntity : organizationEntities) {
            ProfessionalDetail details = new ProfessionalDetail();
            List<Project> projects = new ArrayList<>();

            Organization organization = mapper.entityToModelOrganization(organizationEntity);
            List<Projects> projectEntities = helper.findProjectsByOrganizationId(organizationEntity.getId());
            for (Projects projectEntity : projectEntities) {
                Project project = mapper.entityToModelProject(projectEntity);
                List<TechStacks> techStackEntities = helper.findTechStacksByProjectId(projectEntity.getId());
                List<TechStack> techStacks = mapper.entityToModelTechStack(techStackEntities);
                List<Roles> roleEntities = helper.findRolesByProjectId(projectEntity.getId());
                List<String> roles = mapper.entityToModelRole(roleEntities);
                project.setTechStacks(techStacks);
                project.setRoles(roles);
                projects.add(project);
            }
            details.setOrganization(organization);
            details.setProjects(projects);
            professionalDetails.add(details);
        }
        return professionalDetails;
    }
}
