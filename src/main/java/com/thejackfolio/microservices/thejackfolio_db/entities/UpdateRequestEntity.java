/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.UpdateCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "update_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "event_id")
    private String eventId;
    @Column(name = "category")
    private UpdateCategory category;
    @Column(name = "tournament_id")
    private String tournamentId;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;
    @Column(name = "created_at")
    private Instant createdAt;
}
