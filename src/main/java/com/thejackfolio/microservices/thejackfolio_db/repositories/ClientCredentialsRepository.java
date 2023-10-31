/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.ClientComments;
import com.thejackfolio.microservices.thejackfolio_db.entities.ClientCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientCredentialsRepository extends JpaRepository<ClientCredentials, Integer> {

    Optional<ClientCredentials> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update ClientCredentials cc set cc.verified = true where cc.id = ?1")
    void verifyAccount(Integer clientId);


}
