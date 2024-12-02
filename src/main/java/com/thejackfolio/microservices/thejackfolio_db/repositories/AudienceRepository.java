/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.AudienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AudienceRepository extends JpaRepository<AudienceEntity, Integer> {
    Optional<AudienceEntity> findByEmailAndEventName(String email, String eventName);

    @Query(value="select lan_events.name from lan_events join audience on lan_events.name=audience.event_name " +
            "where audience.email=?1 and lan_events.event_status=3", nativeQuery = true)
    Optional<List<Map<String, Object>>> fetchPastEventsForAudience(String email);

    @Query(value="select lan_events.name from lan_events join audience on lan_events.name=audience.event_name " +
            "where audience.email=?1 and lan_events.event_status=1", nativeQuery = true)
    Optional<List<Map<String, Object>>> fetchFutureEventsForAudience(String email);

    @Query(value="select lan_events.name from lan_events join audience on lan_events.name=audience.event_name " +
            "where audience.email=?1 and lan_events.event_status=2", nativeQuery = true)
    Optional<List<Map<String, Object>>> fetchLiveEventsForAudience(String email);
}
