/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.entities.Teams;
import com.thejackfolio.microservices.thejackfolio_db.enums.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {

    Optional<Teams> findByName(String name);

    Optional<List<Teams>> findAllByEventId(Integer eventId);

    @Transactional
    @Modifying
    @Query("update Teams t set t.teamStatus = ?2 where t.name = ?1")
    void updateTeamStatus(String teamName, TeamStatus status);
}
