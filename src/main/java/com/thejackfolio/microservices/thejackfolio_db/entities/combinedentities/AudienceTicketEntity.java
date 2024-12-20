/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audience_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AudienceTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "ticket_number")
    private String ticketNumber;
    @Column(name = "email_sent")
    private boolean emailSent;
}
