/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities;

import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamWithTeamMate {
    private String eventName;
    private LANTeamStatus status;
    private String name;
    private String email;
    private boolean emailRegistered;
}
