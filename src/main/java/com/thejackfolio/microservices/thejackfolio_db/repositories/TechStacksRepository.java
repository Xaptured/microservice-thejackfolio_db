/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.TechStacks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechStacksRepository extends JpaRepository<TechStacks, Integer> {

    List<TechStacks> findByProjectId(Integer projectId);
}
