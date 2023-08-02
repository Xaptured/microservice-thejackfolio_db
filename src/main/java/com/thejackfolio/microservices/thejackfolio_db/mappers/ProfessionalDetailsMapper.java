/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.mappers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Organizations;
import com.thejackfolio.microservices.thejackfolio_db.entities.Projects;
import com.thejackfolio.microservices.thejackfolio_db.entities.Roles;
import com.thejackfolio.microservices.thejackfolio_db.entities.TechStacks;
import com.thejackfolio.microservices.thejackfolio_db.enums.TechStack;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Organization;
import com.thejackfolio.microservices.thejackfolio_db.models.ProfessionalDetail;
import com.thejackfolio.microservices.thejackfolio_db.models.Project;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionalDetailsMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalDetailsMapper.class);

    public Organizations modelToEntityOrganizations(ProfessionalDetail professionalDetail) throws MapperException {
        Organizations organizationEntity = null;
        try {
            if (professionalDetail != null) {
                organizationEntity = new Organizations();
                organizationEntity.setName(professionalDetail.getOrganization().getName());
                organizationEntity.setFromDate(professionalDetail.getOrganization().getFromDate());
                organizationEntity.setToDate(professionalDetail.getOrganization().getToDate());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
        }
        return organizationEntity;
    }

    public Organization entityToModelOrganization(Organizations organizations) throws MapperException {
        Organization organizationModel = null;
        try {
            if (organizations != null) {
                organizationModel = new Organization();
                organizationModel.setName(organizations.getName());
                organizationModel.setFromDate(organizations.getFromDate());
                organizationModel.setToDate(organizations.getToDate());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return organizationModel;
    }

    public Projects modelToEntityProjects(Project project, Integer organizationId) throws MapperException {
        Projects projectEntity = null;
        try {
            if (project != null) {
                projectEntity = new Projects();
                projectEntity.setName(project.getName());
                projectEntity.setDescription(project.getDescription());
                projectEntity.setOrganizationId(organizationId);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
        }
        return projectEntity;
    }

    public Project entityToModelProject(Projects project) throws MapperException {
        Project projectModel = null;
        try {
            if (project != null) {
                projectModel = new Project();
                projectModel.setName(project.getName());
                projectModel.setDescription(project.getDescription());
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return projectModel;
    }

    public TechStacks modelToEntityTechStacks(TechStack techStack, Integer projectId) throws MapperException {
        TechStacks techStackEntity = null;
        try {
            if (techStack != null) {
                techStackEntity = new TechStacks();
                techStackEntity.setTechStack(techStack.name());
                techStackEntity.setProjectId(projectId);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
        }
        return techStackEntity;
    }

    public List<TechStack> entityToModelTechStack(List<TechStacks> techStacks) throws MapperException {
        List<TechStack> techStacksModels = null;
        try {
            if (techStacks != null && !techStacks.isEmpty()) {
                techStacksModels = new ArrayList<>();
                for (TechStacks techStackEntity : techStacks) {
                    TechStack techStack = TechStack.valueOf(techStackEntity.getTechStack());
                    techStacksModels.add(techStack);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return techStacksModels;
    }

    public Roles modelToEntityRoles(String role, Integer projectId) throws MapperException {
        Roles roleEntity = null;
        try {
            if (StringUtils.isNotEmpty(role)) {
                roleEntity = new Roles();
                roleEntity.setRole(role);
                roleEntity.setProjectId(projectId);
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_MODEL_TO_ENTITY, exception);
        }
        return roleEntity;
    }

    public List<String> entityToModelRole(List<Roles> roles) throws MapperException {
        List<String> roleModels = null;
        try {
            if (roles != null && !roles.isEmpty()) {
                roleModels = new ArrayList<>();
                for (Roles roleEntity : roles) {
                    String role = roleEntity.getRole();
                    roleModels.add(role);
                }
            }
        } catch (Exception exception) {
            LOGGER.info(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
            throw new MapperException(StringConstants.MAPPING_ERROR_ENTITY_TO_MODEL, exception);
        }
        return roleModels;
    }
}
