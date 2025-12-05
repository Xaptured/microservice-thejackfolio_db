/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.UpdateRequestEntity;
import com.thejackfolio.microservices.thejackfolio_db.enums.UpdateCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateRequestRepository extends JpaRepository<UpdateRequestEntity, Integer> {

    List<UpdateRequestEntity> findTop7ByCategoryOrderByCreatedAtDesc(UpdateCategory updateCategory);

    @Query("SELECT u FROM UpdateRequestEntity u WHERE u.category = :category ORDER BY u.createdAt DESC")
    List<UpdateRequestEntity> findByTypeWithLimit(UpdateCategory category, Pageable pageable);
}
