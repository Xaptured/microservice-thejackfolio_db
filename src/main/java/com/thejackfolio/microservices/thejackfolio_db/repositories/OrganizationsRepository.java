/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationsRepository extends JpaRepository<Organizations, Integer> {
}
