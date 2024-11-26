/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Events;
import com.thejackfolio.microservices.thejackfolio_db.entities.LANEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LANEventRepository extends JpaRepository<LANEvent, Integer> {

    Optional<LANEvent> findByName(String eventName);

    boolean existsByEmail(String email);

    @Query(value = "select * from lan_events where email = ?1 and event_status < 2", nativeQuery = true)
    Optional<List<LANEvent>> fetchFutureEventsWRTEmail(String email);

    @Query(value = "select * from lan_events where email = ?1 and event_status = 3", nativeQuery = true)
    Optional<List<LANEvent>> fetchPastEventsWRTEmail(String email);
}
