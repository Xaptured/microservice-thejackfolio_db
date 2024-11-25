/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LANEvent {
    private String name;
    private String gameName;
    private LANAddress address;
    private LANEventDetails eventDetails;
    private LANEventStatus eventStatus;
}
