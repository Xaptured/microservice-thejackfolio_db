/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.EventRules;
import com.thejackfolio.microservices.thejackfolio_db.entities.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamDetailsRepository extends JpaRepository<TeamDetails, Integer> {

    Optional<List<TeamDetails>> findAllByTeamId(Integer teamId);

    Optional<List<TeamDetails>> findAllByEmail(String email);
}
