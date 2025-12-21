/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tournament_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TournamentImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "tournament_name")
    private String tournamentName;
    @Column(name = "image_id")
    private Long imageId;
}
