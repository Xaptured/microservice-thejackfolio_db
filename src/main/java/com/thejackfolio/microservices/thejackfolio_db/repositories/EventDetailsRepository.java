/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, Integer> {

    Optional<EventDetails> findByEventId(Integer eventId);

    Optional<List<EventDetails>> findAllByDate(Date date);
}
