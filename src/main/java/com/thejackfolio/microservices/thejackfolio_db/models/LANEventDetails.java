/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LANEventDetails {
    private LANEventType eventType;
    private BigDecimal prizePool;
    private Integer totalSlots;
    private String date;
}
