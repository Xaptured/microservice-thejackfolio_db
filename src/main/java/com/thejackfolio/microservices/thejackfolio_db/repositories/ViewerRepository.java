/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Viewers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewerRepository  extends JpaRepository<Viewers, Integer> {

    @Query(value = "select * from viewers where event_id = ?1 and email = ?2", nativeQuery = true)
    Optional<Viewers> findViewer(Integer eventId, String email);
}
