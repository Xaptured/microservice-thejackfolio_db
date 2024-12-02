/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import com.thejackfolio.microservices.thejackfolio_db.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Audience {
    private String name;
    private String email;
    private BigDecimal amount;
    private String transactionId;
    private PaymentStatus status;
    private String eventName;
}
