/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.AudienceEntity;
import com.thejackfolio.microservices.thejackfolio_db.entities.SubUserEntity;
import com.thejackfolio.microservices.thejackfolio_db.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubUserRepository extends JpaRepository<SubUserEntity, Integer> {

    boolean existsByEmailAndEventName(String email, String eventName);

    boolean existsByEventName(String eventName);

    Optional<SubUserEntity> findByEmailAndEventName(String email, String eventName);

    @Transactional
    @Modifying
    @Query("update SubUserEntity s set s.isActive = true where s.eventName = ?1")
    void updateActive(String eventName);

    Optional<List<SubUserEntity>> findByIsEmailSent(boolean isEmailSent);
}
