/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.models.Skill;
import com.thejackfolio.microservices.thejackfolio_db.services.SkillsService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Skill", description = "Skill management APIs")
@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @Operation(
            summary = "Save skills",
            description = "Save skills which includes both personal and professional and gives the same skills response with a message which defines whether the request is successful or not.",
            tags = { "skills", "post" }
    )
    @PostMapping("/save-skills")
    public ResponseEntity<Skill> saveSkills(@RequestBody Skill skill) {
        try {
            if (skill == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            skillsService.saveSkills(skill);
            skill.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException mapperException) {
            skill.setMessage(StringConstants.MAPPING_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
        } catch (DataBaseOperationException dataBaseOperationException) {
            skill.setMessage(StringConstants.DATABASE_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(skill);
    }

    @Operation(
            summary = "Get skills",
            description = "It gives the skills as response which includes both personal and professional with a message which defines whether the request is successful or not.",
            tags = { "comments", "get" }
    )
    @GetMapping("/get-skills")
    public ResponseEntity<Skill> getSkills() {
        Skill skill = null;
        try {
            skill = skillsService.getSkills();
            skill.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException mapperException) {
            skill = new Skill();
            skill.setMessage(StringConstants.MAPPING_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
        } catch (DataBaseOperationException dataBaseOperationException) {
            skill = new Skill();
            skill.setMessage(StringConstants.DATABASE_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
        }
        return ResponseEntity.status(HttpStatus.OK).body(skill);
    }
}
