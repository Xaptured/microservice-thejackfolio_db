/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.UpdateCategory;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequest {
    private String eventId;
    private UpdateCategory category;
    private String tournamentId;
    private String title;
    private String message;
    private Instant createdAt;
}
