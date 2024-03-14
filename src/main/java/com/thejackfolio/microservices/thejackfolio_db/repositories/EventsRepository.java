/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {

    Optional<Events> findByName(String name);

    @Query(value = "select * from events where email = ?1 and status < 3", nativeQuery = true)
    Optional<List<Events>> findAllActiveEventsByEmail(String email);

    @Query(value = "select * from events where game_id in (:gameIds) and status = 1", nativeQuery = true)
    Optional<List<Events>> findActiveEventsWrtInterestedGames(List<Integer> gameIds);

    @Transactional
    @Modifying
    @Query("update Events e set e.status = ?2 where e.name = ?1")
    void updateEventStatus(String name, EventStatus status);
}
