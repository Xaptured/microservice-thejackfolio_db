/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.AudienceTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudienceTicketRepository extends JpaRepository<AudienceTicketEntity, Integer> {
    Optional<AudienceTicketEntity> findByEmailAndEventName(String email, String eventName);

    long countByEmailSent(boolean value);

    Optional<List<AudienceTicketEntity>> findByEmailSentFalse();

    @Transactional
    @Modifying
    @Query("update AudienceTicketEntity a set a.emailSent = true where a.id = ?1")
    void updateEmailStatus(Integer id);
}
