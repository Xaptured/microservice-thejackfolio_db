/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {

    Optional<Games> findByName(String gameName);

    @Query(value = "select * from game where name in (:gameNames)", nativeQuery = true)
    Optional<List<Games>> findAllByNames(List<String> gameNames);

    @Query(value = "select * from game where status = 1", nativeQuery = true)
    List<Games> findAllActiveGames();
}
