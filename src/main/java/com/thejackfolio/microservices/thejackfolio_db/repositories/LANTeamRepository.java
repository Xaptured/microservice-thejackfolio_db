/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.LANTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface LANTeamRepository extends JpaRepository<LANTeamEntity, Integer> {

    @Query(value = "select lan_teams.event_name,lan_teams.status,lan_teams.name,lan_team_mates.email,lan_team_mates.email_registered from lan_teams join lan_team_mates on lan_teams.id=lan_team_mates.team_id where lan_team_mates.email=?1 and lan_teams.status=0", nativeQuery = true)
    public Optional<List<Map<String, Object>>> fetchTeamWithTeamMate(String email);

    @Query(value = "select lan_teams.id from lan_teams join lan_team_mates on lan_teams.id=lan_team_mates.team_id where lan_team_mates.email=?1 and lan_teams.event_name=?2", nativeQuery = true)
    public Optional<Map<String, Object>> fetchTeamWithTeamMateAndEventName(String email, String eventName);

    public Optional<List<LANTeamEntity>> findByEventName(String eventName);
}
