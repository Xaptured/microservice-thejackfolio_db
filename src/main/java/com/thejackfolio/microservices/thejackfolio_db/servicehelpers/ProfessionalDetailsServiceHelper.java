/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.servicehelpers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Organizations;
import com.thejackfolio.microservices.thejackfolio_db.entities.Projects;
import com.thejackfolio.microservices.thejackfolio_db.entities.Roles;
import com.thejackfolio.microservices.thejackfolio_db.entities.TechStacks;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.repositories.OrganizationsRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.ProjectsRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.RolesRepository;
import com.thejackfolio.microservices.thejackfolio_db.repositories.TechStacksRepository;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalDetailsServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalDetailsServiceHelper.class);
    @Autowired
    private OrganizationsRepository organizationsRepository;
    @Autowired
    private ProjectsRepository projectsRepository;
    @Autowired
    private TechStacksRepository techStacksRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public Organizations saveOrganization(Organizations organization) throws DataBaseOperationException {
        try{
            organization = organizationsRepository.save(organization);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return organization;
    }

    public Projects saveProject(Projects project) throws DataBaseOperationException {
        try{
            project = projectsRepository.save(project);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return project;
    }

    public void saveTechStack(TechStacks techStack) throws DataBaseOperationException {
        try{
            techStacksRepository.save(techStack);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public void saveRole(Roles role) throws DataBaseOperationException {
        try{
            rolesRepository.save(role);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
    }

    public List<Organizations> findAllOrganizations() throws DataBaseOperationException {
        List<Organizations> organizations = null;
        try{
            organizations = organizationsRepository.findAll();
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return organizations;
    }

    public List<Projects> findProjectsByOrganizationId(Integer organizationId) throws DataBaseOperationException {
        List<Projects> projects = null;
        try{
            projects = projectsRepository.findByOrganizationId(organizationId);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return projects;
    }

    public List<TechStacks> findTechStacksByProjectId(Integer projectId) throws DataBaseOperationException {
        List<TechStacks> techStacks = null;
        try{
            techStacks = techStacksRepository.findByProjectId(projectId);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return techStacks;
    }

    public List<Roles> findRolesByProjectId(Integer projectId) throws DataBaseOperationException {
        List<Roles> roles = null;
        try{
            roles = rolesRepository.findByProjectId(projectId);
        } catch (Exception exception){
            LOGGER.info(StringConstants.DATABASE_ERROR, exception);
            throw new DataBaseOperationException(StringConstants.DATABASE_ERROR, exception);
        }
        return roles;
    }
}
