/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "initiate_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InitiatePaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "merchant_transaction_id")
    private String merchantTransactionId;
    @Column(name = "event_name")
    private String eventName;
}
